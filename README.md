<div align="center">
    <h1>Eras</h1>
    A full stack web application and launcher daemon for launching and accessing Toontown Rewritten information from the web<br>
    <small> ⚠️ Not complete, many features not implemented, not open to the public ⚠️</small>
    <img src="./assets/webpage.png" alt="webpage">
</div>

### Stack
<ul>
    <li>server
        <ul>
            <li>Java</li>
            <li>Spring Boot</li>
        </ul>
    </li>
    <li>launcher
        <ul>
            <li>Java</li>
            <li>Micronaut</li>
            <li>Websockets</li>
        </ul>
    </li>
    <li>frontend
        <ul>
            <li>Typescript</li>
            <li>Angular</li>
            <li>Bootstrap</li>
            <li>Websockets</li>
        </ul>
    </li>
</ul>



### How to run from source
#### Requirements
<ul>
    <li>node.js</li>
    <li>Java 17</li>
    <li>Maven CLI</li>
</ul>

#### Running Madam Chuckle
<ul>
    <li>Clone and cd into repo</li>
    <li>backend
        <ul>
            <li><code>cd server</code></li>
            <li><code>mvn spring-boot:run</code></li>
        </ul>
    </li>
    <li>launcherd
        <ul>
            <li><code>cd launcher</code></li>
            <li><code>mvn mn:run</code></li>
        </ul>
    </li>
    <li>frontend
        <ul>
            <li><code>cd frontend</code></li>
            <li><code>npm install</code></li>
            <li><code>ng serve --open</code></li>
        </ul>
    </li>
</ul>
