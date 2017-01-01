# JInsist JVM mocking library

Motivation for this code: it was done as a weekend kata out of curiosity and frustration why most of the mocking
 libraries are so complicated and how hard it would be to do it **right**. It does not mean I did it right.
 
### Current API
Currently only **ordered expectation verification** is supported. It means mockery will expect invocations in order they
 were added.

```java
  Mockery mockery = new Mockery();
  
  // side effect verification
  mockery.expect(collaborator).call(mock -> mock.firstMethod("input"));
  collaborator.firstMethod("input");
  
  // stub verification
  mockery.expect(collaborator).stub(mock -> mock.firstMethod("input")).returns("output");
  Assert.assertEquals("output", collaborator.firstMethod("input"));
  
  mockery.verify(); // verify is optional

```

### ToDo
- Implement history based reporting
- Add JUnit integration to reduce boilerplate
- Add static mockery creation to look more like other frameworks
- Refactor tests to use mocks
- Support parallel execution
- Add more types of verification: unordered, mixed
