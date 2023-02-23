## Java database connection and operations

#### Basic CRUD

#### Postgres hosted on heroku 
![favicon-32x32](https://github.com/heroku/favicon/raw/master/favicon.iconset/icon_32x32.png)
<img src="https://upload.wikimedia.org/wikipedia/commons/2/29/Postgresql_elephant.svg" alt="Postgres Logo" width="32" height="32">



Tested Using `PgAppRunner class`
````Java

@Autowired

@Component
public class PgAppRunner implements ApplicationRunner {
    CustomerRepository customerRepository;

    @Autowired
    public PgAppRunner(CustomerRepository customerRepository) {this.customerRepository = customerRepository;}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // this is where we tested the different crud operations
        //...
        //....
    }
}
````

#### CRUD operations include



- Find All Customer
````Java
CustomerRepository.findAll();
```` 

- Find All Customer
````Java
CustomerRepository.findById();
```` 
- Find By Name
````Java
CustomerRepository.findByName();
```` 

- Find Customer Page (with limit & offset)
````Java
CustomerRepository.findCustomersPage(limit, offset);
````

- Add Customer
````Java
CustomerRepository.addCustomer(CustomerObject);
````
- Update Customer
````Java
CustomerRepository.updateCustomer(CustomerObject);
````

- Find Country with most customers
````Java
CustomerRepository.findCountryWithMostCustomers();
````
-Find Customer with highest Spending
````Java
CustomerRepository.findCustomerWithHighestSpending();
````
-Find most popular genre for a customer
````Java
CustomerRepository.mostPopGenre(customer_id);

````





