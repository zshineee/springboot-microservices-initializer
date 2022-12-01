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
    this.routeForm = new FormGroup({
      id: new FormControl(),
      predicates: new FormControl(),
      filters: new FormControl(),
      uri: new FormControl(),
      orders: new FormControl(),
      status: new FormControl(),
      description: new FormControl()
    });
  }

}
