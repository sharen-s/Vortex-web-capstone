<template>
  <div id="register" class="text-center">
    <form class="form-register" @submit.prevent="register">
      <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
      <div class="alert alert-danger" role="alert" v-if="registrationErrors">
        {{ registrationErrorMsg }}
      </div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />
      <input
        type="password"
        id="confirmPassword"
        class="form-control"
        placeholder="Confirm Password"
        v-model="user.confirmPassword"
        required
      />
      <router-link :to="{ name: 'login' }">Have an account?</router-link>
      <button class="btn btn-lg btn-primary btn-block" type="submit">
        Create Account
      </button>
    </form>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      } else {
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style>
#register {
    display: flex;
    align-items: center;
    height: 100vh;
    background-color: transparent;
  }
 .form-register {
    background-color: white;
    padding: 40px;
    width: 400px;
    margin: 0 auto;
    border-radius: 4px;
    box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
  }
  .form-register h1 {
    margin-bottom: 20px;
    font-size: 24px;
    font-weight: bold;
    color: #333;
  }

   .form-register:hover{
  transform: scale(1.05);
  border: 1px solid #23468A;

  }
  .form-register label {
    font-weight: bold;
    margin-bottom: 10px;
    display: block;
  }

  .form-register button[type="submit"]:hover {
  background-color: #09A0D8;
  cursor: pointer;
}
 .form-register input[type="text"],
  .form-register input[type="password"] {
    box-shadow: inset 2px 2px 5px rgba(0, 0, 0, 0.2);
    width: 100%;
    padding: 10px;
    margin-bottom: 20px;
    font-size: 16px;
    border-radius: 18px;
    border: 1px solid #DDD;
  }
 .form-register .btn-primary {
    background-color: #23468A;
    color: white;
    font-weight: bold;
    padding: 10px 20px;
    border-radius: 18px;
    border: none;
    margin-top: 20px;
  }
  .form-register a {
    display: block;
    font-size: 15px;
    margin-top: 5px;
    text-align: right;
    color: #23468A;
   }

  @media (max-width: 720px) {
    .form-register {
      width: 70%;
    }
  }

</style>
