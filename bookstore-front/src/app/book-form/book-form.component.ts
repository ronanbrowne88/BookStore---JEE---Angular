import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'bs-book-form',
  templateUrl: './book-form.component.html',
  styles: [],
  encapsulation: ViewEncapsulation.None
})
export class BookFormComponent implements OnInit {

  private books =
    {
      title: "dummy title ",
      description: "dummy description",
      unitCost: "1234",
      isbn: "12344566",
      pages:"356" ,
      Language:"Englishh",
      imageURL: "https://images-na.ssl-images-amazon.com/images/I/510SEck1hNL._SX399_BO1,204,203,200_.jpg",

    }

  constructor() { }

  ngOnInit() {
  }

}
