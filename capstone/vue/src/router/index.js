import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import Forum from "../views/Forum.vue"
import ForumSearchResults from '../views/ForumSearchResults.vue'
import ForumCreationPage from '../views/ForumCreationPage'
import NewPost from '../views/NewPost.vue'
import PostAndReplies from '../views/PostAndReplies.vue'
import SearchResultPage from '../views/SearchResultPage.vue'
import Account from '../views/Account.vue'
import NotFound from '../views/NotFound.vue'



Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/forum/:id",
      name: "forum",
      component: Forum,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/forumSearch",
      name: "forumSearch",
      component: ForumSearchResults,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/createForum",
      name: "createForum",
      component: ForumCreationPage,
      meta: {
        requiresAuth: true
      }
    },

    {
      path: "/forum/new-post/:id",
      name: "newPost",
      component: NewPost,
      meta: {
        requiresAuth: true
      }

    },
    {
      path: "/post/:id",
      name: "postAndReplies",
      component: PostAndReplies,
      meta: {
        requiresAuth: false
      }

    },
    {
      path: '/search/:option/:query',
      name: 'search',
      component: SearchResultPage,
      meta: {
        requiresAuth: false
      }
    },
    
    {
      path: "/account",
      name: "account",
      component: Account,
      meta:{
        requiresAuth: true
      }
    },
    {
      path: '*',
      name: 'notFound',
      component: NotFound,
      meta: {
        requiresAuth: false
      }
    },


  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
