package update

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"net/http"
	"path"

	"github.com/jaczerob/madamchuckle/internal/static"
	"github.com/jaczerob/madamchuckle/internal/util"
)

func NewUpdateClient(executable *util.Executable) *UpdateClient {
	return &UpdateClient{
		executable: executable,
		http:       &http.Client{},
		headers:    static.Headers,
	}
}

func (c *UpdateClient) Update() (err error) {
	updateFiles, err := c.getUpdateFiles()
	if err != nil {
		return
	}

	for _, file := range updateFiles {
		log.Println("updating ", file.Name)

		err := c.downloadBZ2(file)
		if err != nil {
			return err
		}
	}

	return
}

func (c *UpdateClient) getManifest() (manifest map[string]*ManifestData, err error) {
	log.Println("getting manifest")

	data, err := c.get(static.UpdateManifestURL)
	if err != nil {
		return
	}

	err = json.Unmarshal(data, &manifest)
	if err != nil {
		return nil, fmt.Errorf("error parsing json response: %s\n%s", err, data)
	}

	return
}

func (c *UpdateClient) get(url string) (body []byte, err error) {
	resp, err := c.http.Get(url)
	if err != nil {
		return
	}

	defer resp.Body.Close()

	body, err = ioutil.ReadAll(resp.Body)
	if err != nil {
		return
	}

	if resp.StatusCode != http.StatusOK {
		return nil, fmt.Errorf("bad http status: %d", resp.StatusCode)
	}

	log.Println("request OK")
	return
}

func (c *UpdateClient) downloadBZ2(file *UpdateFile) (err error) {
	log.Println("downloading", file.URL)

	resp, err := c.http.Get(file.URL)
	if err != nil {
		return
	}

	if resp.StatusCode != http.StatusOK {
		return fmt.Errorf("bad http status: %d", resp.StatusCode)
	}

	file.Read(resp.Body)
	return
}

func (c *UpdateClient) getUpdateFiles() (updateFiles []*UpdateFile, err error) {
	log.Println("checking for updates")

	manifest, err := c.getManifest()
	if err != nil {
		return
	}

	for file, fileManifest := range manifest {
		if !c.platformIsIn(fileManifest.Only) {
			continue
		}

		filepath := path.Join(c.executable.Directory, file)
		updateFile, err := NewUpdateFile(file, filepath, static.UpdateDownloadURL+fileManifest.DL)
		if err != nil {
			return nil, err
		}

		if updateFile.Exists {
			if updateFile.Hash == fileManifest.Hash {
				continue
			} else {
				log.Println("out of date, queueing for update", file)
			}
		} else {
			log.Println("does not exist, queuing for download", file)
		}

		updateFiles = append(updateFiles, updateFile)
	}

	return
}

func (c *UpdateClient) platformIsIn(only []string) bool {
	for _, platform := range only {
		if platform == c.executable.Platform {
			return true
		}
	}

	return false
}
