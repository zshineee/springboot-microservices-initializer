import {Component, OnInit} from '@angular/core';
import {UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {HttpService} from "../common/http/http.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  validateForm!: UntypedFormGroup;

  submitForm(): void {

    const username = this.validateForm.controls['username'].value;
    const password = this.validateForm.controls['password'].value;

    if (this.http.post("auth/login", {username: username, password: password})) {
      this.router.navigateByUrl('/layout').then();
    }


    // if (this.validateForm.valid) {
    //   console.log('submit', this.validateForm.value);
    // } else {
    //   Object.values(this.validateForm.controls).forEach(control => {
    //     if (control.invalid) {
    //       control.markAsDirty();
    //       control.updateValueAndValidity({onlySelf: true});
    //     }
    //   });
    // }
  }

  constructor(private fb: UntypedFormBuilder, private router: Router, private http: HttpService) {
  }

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      username: [null, [Validators.required]],
      password: [null, [Validators.required]],
      remember: [true]
    });
  }
}
