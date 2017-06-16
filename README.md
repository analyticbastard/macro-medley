# macro-medley

![macro-medley Build Status](https://circleci.com/gh/analyticbastard/macro-medley.svg?&style=shield&circle-token=4c2042ac8fb21debecbd6cc6fe46ddfb76f462b6 "macro-medley Build Status")


Medley of utility macros for a more expressive idiomatic code

[weavejester/medley](https://github.com/weavejester/medley) specifically includes only functions so macros are explicitly excluded. This library intends to provide a home for all those macros that are very useful but self-contained and small enough not to be part of their own framework or purpose library. 

## Usage

Include this dependency in your Leiningen or Boot project.

```
[macro-medley 0.1.0]
```

Require the namespace as usual

```clojure
(require '[macro-medley.core :refer :all])
```

Now you can use the library macros

### Self mappers (or convenient zipmap)

A pattern you may have come across is this

```clojure
(zipmap [:a :b :c] [a b c])
```

which is needed to build a map as an input to a map destructuring function (or build the map manually). The bindings are very often named identically to the input map's keys.

This set of functions remove the redundancy of having to reapeat the binding symbol as a keyword and reduces the boilerplate.

```clojure
(let [a 1 b 2]
  (selfmapk a))
```

yields `{:a 1}`.

```clojure
(let [a 1 b 2]
  (selfmapk a b))
```

evaluates to `{:a 1 :b 2}`

Several flavours provided: the map's key types can be plain key, qualified key, string and symbol. For example, when using the following command within the REPL

```clojure
(let [a 1 b 2]
  (selfmapq a b))
```

we would get namespace-qualified keywords as map keys `{:user/a 1 :user/b 2}`, assuming the current namespace is the default REPL namespace `user`.

### Multiple-binding when-let and if-let

Despite having to decide what a multiple-binding when-let or if-let has to do, if evaluating the body if all, some, the first or the last binding is not falsey, these forms are often very handing to reduce the amout of let blocks and increase the legibility and declarativeness of the code (equivalently, reduce the imperativeness).

```clojure
(when-let* [a 1
            b 2]
 [a b])
```

would evaluate to `[1 2]`. However

```clojure
(when-let* [a 1
            b nil]
 [a b])
```

would evaluate to `nil`. Similarly

```clojure
(if-let* [a 1
            b 2]
 [a b]
 [3 4])
```

would evaluate to `[1 2]` and

```clojure
(if-let* [a 1
          b nil]
 [a b]
 [3 4])
```

would yield `[3 4]`. When the else form is absent, the result is `nil` as expected.


## Contributing

Remember: never do with a macro what you can do with a function.
If your utility code is a function (i.e., something that processes run time data) then I suggest you open your pull request to [weavejester/medley](https://github.com/weavejester/medley)
 
Otherwise, if your code operates with compiler data structures, such as binding names and the like, then I will be more than happy to include your utilities in the library.

## License

Copyright © 2017 Javier Arriero

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
