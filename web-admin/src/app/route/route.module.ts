import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouteRoutingModule } from './route-routing.module';
import { RouteComponent } from './route.component';
import {NzTableModule} from "ng-zorro-antd/table";
import {NzDividerModule} from "ng-zorro-antd/divider";


@NgModule({
  declarations: [
    RouteComponent
  ],
  imports: [
    CommonModule,
    RouteRoutingModule,
    NzTableModule,
    NzDividerModule
  ]
})
export class RouteModule { }
