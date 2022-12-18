import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {LoginModule} from "./login/login.module";
import {LayoutModule} from "./layout/layout.module";
import {NzMessageModule} from "ng-zorro-antd/message";
@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    LoginModule,
    LayoutModule,
    NzMessageModule
  ],
  providers: [
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
