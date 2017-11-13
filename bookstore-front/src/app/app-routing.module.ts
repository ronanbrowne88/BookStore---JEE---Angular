import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BookListComponent} from "./book-list/book-list.component";
import {BookFormComponent} from "./book-form/book-form.component";
import {BookDetailsComponent} from "./book-details/book-details.component";

const routes: Routes = [

  {path: '', component: BookListComponent},
  {path: 'book-list', component: BookListComponent},
  {path: 'book-form', component: BookFormComponent},
  {path: 'book-details/:bookId', component: BookDetailsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
