import { Component, OnInit, ViewEncapsulation } from '@angular/core';

@Component({
  selector: 'bs-book-list',
  templateUrl: './book-list.component.html',
  styles: [],
  encapsulation: ViewEncapsulation.None
})
export class BookListComponent implements OnInit {

  private  nbBook: number = 2;

  books = [
    {
      id: "1",
      title: "dummy title 1",
      description: "dummy description 1",
      imageURL: "https://images-na.ssl-images-amazon.com/images/I/510SEck1hNL._SX399_BO1,204,203,200_.jpg",
    },
    {
      id: "2",
      title: "dummy title 2",
      description: "dummy description 2",
      imageURL: "https://d1w7fb2mkkr3kw.cloudfront.net/assets/images/book/lrg/9780/7515/9780751565355.jpg",
    }
  ]

  constructor() { }

  ngOnInit() {
  }

}
