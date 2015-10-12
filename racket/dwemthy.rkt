#lang racket

;; Types

(struct weapon (name function))
(struct creature (name life strength charisma weapon) #:mutable)
(struct hero creature (bombs weapons) #:mutable)

;; Configuration

(define interactive? #f)

;; Core

(define (next-key)
  (if interactive?
      (read)
      (let ([key (random 4)])
        (list-ref '[^ / % *] key))))

(define (dead? c)
  (<= (creature-life c) 0))

(define (alive? c)
  (not (dead? c)))

(define (attack attacker defender weapon)
  (let* ([damage (+ 1 (random (+ (creature-strength attacker) weapon)))])
    (printf "~a HITS ~a FOR ~a\n" (creature-name attacker) (creature-name defender) damage)
    (let* ([power-up (random (creature-charisma defender))]
           [power-up-check (= (modulo power-up 9) 0)]
           [power-up-life (if power-up-check (quotient power-up 4) 0)]
           [new-life (- (+ (creature-life defender) power-up-life) damage)])
      (set-creature-life! defender new-life)
      (if (<= new-life 0)
          (printf "~a has died\n" (creature-name defender))
          (void)))))

(define (fight hero enemy weapon)
  (if (dead? hero)
      (println "~a is dead, so can't fight" (creature-name hero))
      (begin
        (attack hero enemy weapon)
        (if (alive? enemy)
            (attack enemy hero (creature-weapon enemy))
            (void)))))

(define (battle hero enemies)
  (if (empty? enemies)
      (println "wow, you decimated dwemthy's array!")
      (let* ([enemy (first enemies)]
             [weapon-key (next-key)]
             [weapon (hash-ref (hero-weapons hero) weapon-key)]
             [weapon-fn (weapon-function weapon)])
        (printf "using ~a\n" (weapon-name weapon))
        (weapon-fn hero enemy)
        (cond
          [(dead? hero) (void)]
          [(dead? enemy) (battle hero (rest enemies))]
          [else (battle hero enemies)]))))

;; Weapons

(define [boomerang hero enemy]
  (fight hero enemy 13))

(define [sword hero enemy]
  (fight hero enemy (random (+ 4 (* (modulo (creature-life enemy) 10)
                                    (modulo (creature-life enemy) 10))))))

(define [lettuce hero enemy]
  (let ([bonus-life (random (creature-charisma hero))])
    (set-creature-life! hero (+ (creature-life hero) bonus-life))
    (printf "Healthy lettuce gives ~a life points\n" bonus-life)
    (fight hero enemy 0)))

(define [bombs hero enemy]
  (if (> (hero-bombs hero) 0)
      (begin
        (set-hero-bombs! hero (- (hero-bombs hero) 1))
        (fight hero enemy 86))
      (println "Oops... out of bombs...")))

(define weapons
  (hash '^ (weapon 'boomerang boomerang)
        '/ (weapon 'sword sword)
        '% (weapon 'lettuce lettuce)
        '* (weapon 'bombs bombs)))

;; Play

(define rabbit (hero 'rabbit 10000 250 44 40 3 weapons))

(define dwemthys-array
  (list (creature 'industrial-raver-monkey 46 35 91 2)
        (creature 'dwarven-angel 540 6 144 50)
        (creature 'assistant-vice-tentacle-and-ombudsman 320 6 144 50)
        (creature 'teeth-deer 655 192 19 109)
        (creature 'intrepid-decomposed-cyclist 901 560 422 105)
        (creature 'dragon 1340 451 1020 939)))

(battle rabbit dwemthys-array)
