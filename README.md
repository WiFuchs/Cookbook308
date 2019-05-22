# Cookbook308
Java App to keep track of recipes

##API Endpoints
All API endpoints are described here

###Recipe

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
   
