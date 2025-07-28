import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './users.html',
  styleUrls: ['./users.css']
})
export class Users implements OnInit {
  users: any[] = [];
  selectedUser: any = null;
  newUser: any = {
    email: '',
    password: '',
    fullName: '',
    role: 'EMPLOYEE',
    enabled: true
  };
  apiUrl = 'https://backend.task-hub.tech/api/users';

  showDeleteModal = false;
  showSuccessModal = false;
  successMessage = '';
  userToDelete: any = null;

  constructor(private http: HttpClient) {}

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.http.get<any[]>(this.apiUrl).subscribe(data => {
      this.users = data;
    });
  }

  addUser() {
    this.http.post(this.apiUrl, this.newUser).subscribe(() => {
      this.successMessage = `User ${this.newUser.fullName} has been added successfully.`;
      this.showSuccessModal = true;
      this.newUser = {
        email: '',
        password: '',
        fullName: '',
        role: 'EMPLOYEE',
        enabled: true
      };
      this.loadUsers();
    });
  }

  editUser(user: any) {
    this.selectedUser = { ...user };
  }

  updateUser() {
    this.http.put(`${this.apiUrl}/${this.selectedUser.id}`, this.selectedUser).subscribe(() => {
      this.successMessage = `User ${this.selectedUser.fullName} has been updated successfully.`;
      this.showSuccessModal = true;
      this.selectedUser = null;
      this.loadUsers();
    });
  }

  cancelEdit() {
    this.selectedUser = null;
  }

  confirmDeleteUser(user: any) {
    this.userToDelete = user;
    this.showDeleteModal = true;
  }

  deleteUserConfirmed() {
    if (!this.userToDelete) return;
    this.http.delete(`${this.apiUrl}/${this.userToDelete.id}`).subscribe(() => {
      this.showDeleteModal = false;
      this.userToDelete = null;
      this.loadUsers();
    });
  }

  cancelDelete() {
    this.userToDelete = null;
    this.showDeleteModal = false;
  }

  closeSuccessModal() {
    this.successMessage = '';
    this.showSuccessModal = false;
  }
}
