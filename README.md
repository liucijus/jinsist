# JInsist JVM mocking library [![Travis](https://img.shields.io/travis/rust-lang/rust.svg)](https://travis-ci.org/liucijus/jinsist)

Motivation for this code: it was done as a weekend kata out of curiosity and frustration why most of the mocking
 libraries are so complicated and how hard it would be to do it **right**. It does not mean I did it right.
 
### Current API
Currently only **ordered expectation verification** is supported. It means mockery will expect invocations in order they
 were added.

```java
  Mockery mockery = new Mockery();
  Collaborator collaborator = mockery.mock(Collaborator.class);
  
  // side effect verification
  mockery.expect(collaborator).command(mock -> mock.firstMethod("input"));
  collaborator.firstMethod("input");
  
  // stub verification
  mockery.expect(collaborator).query(mock -> mock.firstMethod("input")).returns("output");
  Assert.assertEquals("output", collaborator.firstMethod("input"));
  
  mockery.verify(); // verify is optional

```

### ToDo
- [x] ~~Implement history based reporting~~
- [ ] Add JUnit integration to reduce boilerplate
- [ ] Add static mockery creation to look more like other frameworks
- [ ] Refactor tests to use mocks
- [ ] Support parallel execution
- [ ] Add more types of verification: unordered, mixed
- [ ] Add possibility to match argument
