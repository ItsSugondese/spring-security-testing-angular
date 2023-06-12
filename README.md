spring boot is working on port 9192.
MySQL database is being used.

As for angular, some of the component name are confusing soo make sure to check app-routing.module.ts file but let me brifly summerize few.

1. The redirect to /adminHompage is where all the posting takes place
2. useHash in routing is  set to true becuase I was getting MIME error when trying to using dynamic routing from UserHompage component to NewsComponent.
3. app/homepage folder has 2 sub folder one is user-homepage and another one is admin-homepage.
