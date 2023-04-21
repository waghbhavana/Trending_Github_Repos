# Trending_Github_Repos

An Android App which shows lists of the trending repositories from Github.



#### Component used
* Model-View-ViewModel (MVVM) pattern 
* Retrofit
* RoomDatabse
* dagger2 Hilt


 
 #### The app includes the following main components:
 
* A web api service. Used Retrofit to get data from API
* A local database that servers as sourse of data when no internetconnection available. User Room Database. 
* A repository that works with the database and the api service, providing a unified data interface.
* A ViewModel that provides data specific for the UI.
* The UI, which shows a visual representation of the data in the ViewModel.
* Persist data on orientation change



<img src="https://user-images.githubusercontent.com/6592294/233555784-7255b5d5-533a-4646-987a-3895dbb38c96.png" width="500" height="800">


<img src="https://user-images.githubusercontent.com/6592294/233555793-8765bb6e-6e11-4d50-9737-6419fa84dd4b.png" width="500" height="800">


<img src="https://user-images.githubusercontent.com/6592294/233555798-5b81e8cc-9f8f-4f78-ab38-a08294f738de.png" width="500" height="800">


<img src="https://user-images.githubusercontent.com/6592294/233555800-f28675e2-e8f4-40dc-93e0-5693629ef94f.png" width="500" height="800">
