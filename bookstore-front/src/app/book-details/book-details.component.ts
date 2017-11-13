import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {Router} from '@angular/router';


@Component({
  selector: 'bs-book-details',
  templateUrl: './book-details.component.html',
  styles: [],
  encapsulation: ViewEncapsulation.None
})
export class BookDetailsComponent implements OnInit {

  book =
    {
      title: "dummy title ",
      description: "dummy description",
      unitCost: "1234",
      isbn: "12344566",
      pages: "356",
      Language: "English m8",
      imageURL: "http://image.com"
    }

  //inject router
  constructor(private router: Router) {
  }

  ngOnInit() {
  }

  delete() {
    //invoke REST API
    this.router.navigate(['/book-list']);
  }

}
