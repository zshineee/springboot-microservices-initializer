import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, UntypedFormGroup} from "@angular/forms";
import {User} from "../user.component";

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})
export class UserFormComponent implements OnInit {

  @Input() param !: User;
  userForm!: UntypedFormGroup;
  password = false;

  constructor() {
  }

  ngOnInit(): void {
    if (this.param != null) {
      this.password = true;
    }
    this.userForm = new FormGroup({
      username: new FormControl(this.param == null ? '' : this.param.username),
      fullname: new FormControl(this.param == null ? '' : this.param.fullname),
      status: new FormControl(this.param == null ? '' : this.param.status.toString()),
      supper: new FormControl(this.param == null ? '' : this.param.supper.toString()),
      password: new FormControl(this.param == null ? '' : this.param.password),
      remark: new FormControl(this.param == null ? '' : this.param.remark)
    });
  }

}
