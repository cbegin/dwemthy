# Dwemthy's Array in Racket

This is an initial non-meta implementation of Dwemthy's Array in Racket. 

Because lists are so primitive in Racket, a meta-programming approach would 
either require heavy use of macros (custom syntax) or Racket classes. But
in the end, it seems like it might be counterproductive, as metaprogramming
in this case would likely not yield advantages as are realized in a language
like Ruby.
