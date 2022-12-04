import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, UntypedFormGroup} from "@angular/forms";
import {Route} from "../route.component";

@Component({
  selector: 'app-route-form',
  templateUrl: './route-form.component.html',
  styleUrls: ['./route-form.component.css']
})
export class RouteFormComponent implements OnInit {
  @Input() param !: Route;
  routeForm!: UntypedFormGroup;

  constructor() {
  }

  ngOnInit(): void {
    if (this.param == undefined || null) {
      this.routeForm = new FormGroup({
        id: new FormControl(),
        predicates: new FormControl(),
        filters: new FormControl(),
        uri: new FormControl(),
        orders: new FormControl(),
        status: new FormControl(),
        description: new FormControl()
      });
    } else {
      this.routeForm = new FormGroup({
        id: new FormControl(this.param.id),
        predicates: new FormControl(this.param.predicates),
        filters: new FormControl(this.param.filters),
        uri: new FormControl(this.param.uri),
        orders: new FormControl(this.param.orders),
        status: new FormControl(this.param.statusString),
        description: new FormControl(this.param.description)
      });
    }
  }

}
