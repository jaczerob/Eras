import { Component, OnInit } from '@angular/core';
import { ReleaseNotes } from 'src/app/models/release-notes';
import { ToontownService } from 'src/app/services/toontown.service';

@Component({
  selector: 'app-release-notes',
  templateUrl: './release-notes.component.html',
  styleUrls: ['./release-notes.component.css']
})
export class ReleaseNotesComponent implements OnInit {
  releaseNotes!: ReleaseNotes;
  formattedReleaseNotes!: Map<String, String>;

  constructor(private toontownService: ToontownService) { }

  ngOnInit(): void {
    this.toontownService.getReleaseNotes().subscribe((releaseNotes) => {
      this.releaseNotes = releaseNotes;
      this.formattedReleaseNotes = new Map<String, String>();

      console.log(releaseNotes.body);
      let categories = releaseNotes.body.split(/\r\n\r\n/);
      categories.forEach((category) => {
        let parts = category.split(/\r\n/);
        let title = parts[0].substring(1);

        let description = '';
        parts.slice(1).forEach((part) => description += (part.substring(2) + '<br>'));

        this.formattedReleaseNotes.set(title, description);
      });
    });
  }
}
