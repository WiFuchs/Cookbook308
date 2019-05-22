# Cookbook308
Java App to keep track of recipes

## API Endpoints
All API endpoints are described here

### User
* `/users/sign-up` create a new user, username must be unique
* `/login` login user, returns JWT as Authorization header
```javascript
{
   "username":username,
   "password":password
}
```

### Recipe

* `GET /recipes`
   return list of all recipes owned/saved by the user
* `POST /recipes`
   persist posted recipe to database
* `GET /recipes/user/{id}`
   return list of all public recipes owned by user identified by {id}
* `GET /recipes/{id}`
   return a single recipe identified by {id}, as long as it is owned by the user
* `PUT /recipes/{id}`
   update the recipe identified by {id}, if it is owned by the user. Create recipe if none exists. Has the side effect of deleting annotations annotations associated with the recipe
* `DELETE /recipes/{id}`
   delete the recipe identified by {id}, if it is owned by the user.
```javascript
{
   "title": "Prototypical Recipe (create)",
   "cookTime": 15,
   "prepTime": 10,
   "difficulty": 10,
   "ingredients": [
      {
         "quantity": 5,
         "units": "cups",
         "ingredient": "Magic"
      },
      {
         "quantity": 5,
         "units": "Pounds",
         "ingredient": "Butter"
      }
   ],
   "isPublic": true,
   "rating": 5,
   "source": "manual",
   "steps": [
      {
         "step": "Soften Butter"
      },
      {
         "step": "Add Magic"
      },
      {
         "step": "Season to taste"
      }
   ]
}
```
   
