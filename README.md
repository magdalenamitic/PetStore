# PetStore
PetStore project is used for testing the functionality of the site

Target URL: ``https://petstore.octoperf.com/``<br>
Target Browser: Chrome<br>
Browser version: 80.0.3987.149

### What was tested
- Enter store
- Menu link validity (left menu, top menu, image menu, sign in)
- User registration
- User login
- Adding to cart
- Cart validity (all added item are correct)
- Cookie deletion
- Price matching

### The project has 3 packages
- pages
- tests
- utils
All packages are located in the ``src`` folder

### Package ``pages`` contains 
- CartPage
- HomePage
- Page
- PetStoreMenuPage
- RegistrationPage
- SignInPage
- StoreItemPage

### Package ``tests`` contains
- EnterStoreTest
- PetStoreMenuTest
- RegistrationTest
- SignInTest
- StoreCartTest
- TestTemplate

### Package ``utils`` contains
- ExcelUtils