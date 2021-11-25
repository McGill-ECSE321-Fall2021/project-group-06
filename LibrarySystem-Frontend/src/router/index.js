import Vue from 'vue'
import Router from 'vue-router'
//import Hello from '@/components/Hello'
import Login from '@/components/Login'
import Online from '@/components/Online'
import Offline from '@/components/Offline'
import LibrarianLogin from '@/components/LibrarianLogin'
import Librarian from '@/components/Librarian'
import OpeningHours from '@/components/OpeningHours'
import HeadLibrarian from '@/components/HeadLibrarian'
import Media from '@/components/Media'
Vue.use(Router)

export default new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'Hello',
    //   component: Hello
    // },
    
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/Online',
      name: 'Online',
      component: Online
    },
    {
      path: '/Offline',
      name: 'Offline',
      component: Offline
    },
    {
      path: '/LibrarianLogin',
      name: 'LibrarianLogin',
      component: LibrarianLogin
    },

    {
      path: '/Librarian',
      name: 'Librarian',
      component: Librarian
    },
    {
      path: '/OpeningHours',
      name: 'OpeningHours',
      component: OpeningHours
    },
    {
      path: '/HeadLibrarian',
      name: 'HeadLibrarian',
      component: HeadLibrarian
    },
    {
      path: '/Media',
      name: 'Media',
      component: Media
    },
  ]
})
