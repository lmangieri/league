import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RestService } from '../rest.service';

export class User {
  email : string;
  name : string;
  nick : string;
}

export class UserFormError {
  status : string;
  errorCode : string;
  message : string;

  clean() {
    this.status = "";
    this.errorCode = "";
    this.message = "";
  }

  assign (userFormError : UserFormError) {
    this.errorCode = userFormError.errorCode; 
    this.message = userFormError.message;
    this.status = userFormError.status;
  }
}

@Component({
  selector: 'app-userform',
  templateUrl: './userform.component.html',
  styleUrls: ['./userform.component.scss']
})

export class UserformComponent implements OnInit {
  userForm: FormGroup;
  restService: RestService;
  userFormError : UserFormError;

  constructor(private fb: FormBuilder, private rService: RestService) { }

  ngOnInit() {
    this.restService = this.rService;

    this.userForm = this.fb.group({
      email : ['',[
        Validators.required,
        Validators.email
      ]],
      name : 'sasas',
      nick : ''
    });
    
    this.userFormError = new UserFormError();
    this.userFormError.status = '';
    this.userFormError.message = '';
    this.userFormError.errorCode = '';
  }

  get email() {
    return this.userForm.get('email');
  }

  get name() {
    return this.userForm.get('name');
  }
  get nick() {
    return this.userForm.get('nick');
  }

  register() {
    this.restService.registerUser(this.userForm.value, this.userFormError);
    return 1;
  }
}


