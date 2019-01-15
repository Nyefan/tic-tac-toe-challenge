###Compile and launch webservice

```bash
mvn spring-boot:run
```

###Run unit tests
```bash
mvn clean test
```

###Run integration tests
```bash
mvn clean test -Dtest-groups=integration
```

###Smoke test

On Windows:

```bash
curl -X POST -H "Content-Type: application/json" "localhost:8080/anaconda/tictactoe/v1/api/games" --data "{""playerOne"":""one"", ""playerTwo"":""two""}"
curl -X GET localhost:8080/anaconda/tictactoe/v1/api/games
curl -X GET localhost:8080/anaconda/tictactoe/v1/api/games/d870d42e-d37c-4edf-b9b5-7b4a651704c2
curl -X POST -H "Content-Type: application/json" "localhost:8080/anaconda/tictactoe/v1/api/games/d870d42e-d37c-4edf-b9b5-7b4a651704c2" --data "{""player"":""one"", ""x"":1, ""y"":1}"
```

On Mac/Linux

```bash
curl -X POST -H "Content-Type: application/json" localhost:8080/anaconda/tictactoe/v1/api/games --data '{"playerOne":"one", "playerTwo":"two"}'
curl -X GET localhost:8080/anaconda/tictactoe/v1/api/games
curl -X GET localhost:8080/anaconda/tictactoe/v1/api/games/d870d42e-d37c-4edf-b9b5-7b4a651704c2
curl -X POST -H "Content-Type: application/json" localhost:8080/anaconda/tictactoe/v1/api/games/d870d42e-d37c-4edf-b9b5-7b4a651704c2 --data '{"player":"one", "x":1, "y":1}'
```

Expected output

```bash
{"playerOne":"one","playerTwo":"two","board":{"board":[[null,null,null],[null,null,null],[null,null,null]]},"id":"d870d42e-d37c-4edf-b9b5-7b4a651704c2"}

[{"playerOne":"one","playerTwo":"two","board":{"board":[[null,null,null],[null,null,null],[null,null,null]]},"id":"d870d42e-d37c-4edf-b9b5-7b4a651704c2"}]

{"playerOne":"one","playerTwo":"two","board":{"board":[[null,null,null],[null,null,null],[null,null,null]]},"id":"d870d42e-d37c-4edf-b9b5-7b4a651704c2"}

{"playerOne":"one","playerTwo":"two","board":{"board":[[null,null,null],[null,0,null],[null,null,null]]},"id":"d870d42e-d37c-4edf-b9b5-7b4a651704c2"}
```