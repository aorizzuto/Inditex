# Changelog

All notable changes to this project will be documented in this file

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/)

Dates in this file will have the following format: MM/DD/YYYY

1) Branch "main" was created with repository
2) I will work with branching methodology. Each branch will be one feature (one ticket in JIRA)
3) When one feature is finished, branch will be merged with "main" branch as you can see in [github](https://github.com/aorizzuto/Inditex)
4) Each branch will have multiple commits explaining what it does each commit
5) Each branch will have a pull request (merge request) that was merged to branch main
6) The project needs to have three main branches: "main: DEV", "staging:QA" and "prod". In this challenge I only created main branch

### NOTE:
- We could use Project Reactor to this project but this depends on how many requests by second in this API will have.
- Using Project Reactor allows parallel thread execution. We need to use Mono/Flex and subscribe Monos to perform the cycle.

## [0.7.0] - 03/31/2022 - Branch: feature/fixes
## Modified
- Interface used to call service in Controller
- ErrorCode was modified to be used as a Enum
  - It is more organized to use it like that. We can handle both message and HttpStatus
- GlobalExceptionHandler was modified to accept new ErrorCode structure and new exception custom classes.
- Removing unused imports
- Postman collection was fixed to use valid dates
- Fix in tests to expect custom Exceptions

## Added
- Qualifier annotation used 
  - Just to let you know I'm aware of the existence of it and its use.
  - I know it is not obligatory to use when we have only one implementation of the interface
- BusinessException created to handle exceptions related to business
  - This exception works with ErrorCode instead of a String like Exception class
  - This exception extends from Exception class
- BadDateFormatException is a custom exception to handle all issues related to Dates in DateValidator
  - It extends from BusinessException
- InvalidBrandException is a custom exception to handle all issues related to Brands or Chain
  - It extends from BusinessException
- InvalidProductException is a custom exception to handle all issues related to Products
  - It extends from BusinessException
- Using new Custom Exceptions in validators

## Modified

## [0.6.0] - 03/10/2022 - Branch: feature/tests
## Added
- Tests in Controller
- Tests in Service
- Tests in Validations
- Use of @ParameterizedTest, @Test and @Sql
- All working
- Coverage Image --> 90% coverage

## [0.5.0] - 03/10/2022 - Branch: feature/handle-exceptions
## Added
- Image when exception occurs
- Global exception handler to handle exceptions
- ErrorCode file with all error messages
- ResponseEntityBody is a class with the body to response in case of error

## Modified
- New image for success request
- DateValidator with new formatter

## [0.4.0] - 03/09/2022 - Branch: feature/adding-logic-endpoint

## Added
- Validators for input request.
  - Date format validator
  - Product validator if request has negative product Id
  - Chain validator if request has negative branch Id
- Image directory to show project functionality.
  - database image
  - success request image
- Request params to controller
- More logic in PriceConverter to convert records to response
- Exception package created to handle exceptions
- Logic with records in service to get final response. Search max priority record, find records in repository, convert records in response
- Creation of query in repository to get the records that we are looking for

## [0.3.0] - 03/09/2022 - Branch: feature/price-endpoint

## Added
- PriceController
- Price endpoint with its parameters
- PriceService
- DTO to return in controller
- Interface to use Service
- Validate and process methods implementation will be created on in another branch

## [0.2.0] - 03/08/2022 - Branch: feature/adding-h2-dependencies-scripts

## Added
- Maven dependency
- data.sql with table and rows to be created
- initial-inserts.json with initial script. There are two ways to do this:
  - Using jackson library to convert json file to list of Object and then using @EventListener annotation to listen Bean cycle ends. When this happens (After Spring startup) the inserts are done. To do this we can use "repository.save()". 
  - Create inserts queries and add it in data.sql script.
    
  I will use the inserts queries option, but I will leave working and commented the jackson way just to show how it's done
- gitignore
- InitConfig using @EventListener annotation on Bean cycle
- Main Configuration with @Configuration annotation
- PriceDTO, Price entity and PriceConverter to switch between both
- IPricesRepository to handle JPA
- EnvironmentUtils to work with Spring environments (local, dev, prod)
- JsonUtils to work with Json files (In this case, 2nd method to insert records to database)
- application.properties to configure the environment


## [0.1.0] - 03/08/2022 - Branch: feature/create-package-structure

### Added
- Package structure
- Adding CHANGELOG with the step by step

### NOTE
- "img-just-for-challenge" was created only for this challenge to show all steps in the development.

