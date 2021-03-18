(set-option :produce-proofs true)
(declare-const n Int) ; the numebr of distinct subset(trust domains) in the use-case
(define-sort Conf () (Array Int Bool)) ; confidentiality representation
(define-sort QSlices () (Array Int Int)) ; the quorum slices (This set of subsets)
(define-sort QSystem () (Array Int (Array Int Int))) ; the complete quorum system
(define-sort PSize () (Array Int Int)) ; the size array for the all the principles

(declare-const principles PSize)

(define-fun iniQs ((qs QSlices)) Bool
  (forall ((x Int)) (=> (>= x n) (= 0 (select qs x)))))

(define-fun iniQ ((q QSystem)) Bool
  (forall ((x Int) (y Int)) (=> (and (>= x n) (>= y n)) (= 0 (select (select q x) y)))))

(define-fun iniC ((c Conf)) Bool
  (forall ((x Int)) (=> (>= x n) (= false (select c x)))))

(define-fun cLe ((c1 Conf) (c2 Conf)) Bool ; confidentiality c1 lower than c2
  (forall ((i Int)) (=> (select c2 i) (select c1 i))))

(define-fun cLeH ((c1 Conf) (h QSlices)) Bool ; confidentiality c1 lower than c2
  (forall ((i Int)) (=> (> (select h i) 0) (select c1 i))))

(define-fun range ((r Int) (i Int)) Bool ; The range constraint for index 0 <= i < r
  (and (>= i 0) (< i r))
)

(define-fun nonCheck ((q QSlices)) Bool
  (forall ((i Int))
    (=> (range n i) (= (select q i) 0))    
 ))

(define-fun sLe ((s1 QSlices) (s2 QSlices)) Bool ; comparision only involves times_union
  (forall ((x Int))
  (=> (range n x)
    (>= (select s2 x) (select s1 x))
  )))

(define-fun projection ((qs QSlices) (h QSlices)) Bool 
  (sLe qs h))

(define-fun sL ((s1 QSlices) (s2 QSlices)) Bool ; this can be changed to exist, current constraint is more restrictive
  (exists ((x Int))
  (=> (and (range n x) (not (nonCheck s2)))
    (> (select s2 x) (select s1 x))
  )))

(define-fun bLe ((b1 QSystem) (b2 QSystem)) Bool ; integrity and availability comparision with union: b1 is lower than b2
  (forall ((y Int))
    (=> (range n y) 
      (exists ((z Int)) (and (sLe (select b1 y) (select b2 z)) (range n z))
        ))))

(define-fun confQ ((c Conf) (q QSystem)) Bool
  (forall ((x Int) (y Int))
  (=> (and (and (range n x) (range n y)) (> (select (select q y) x) 0))
  (select c x))))

(define-fun cIntegrity ((b QSystem) (q QSystem) (h QSlices)) Bool
  (forall ((i1 Int) (i2 Int))
    (=> (and (and (range n i1) (range n i2)) (projection (select q i2) h))
      (sL (select b i1) (select q i2)))))

(define-fun availabilityC ((b QSystem) (q QSystem)) Bool
  (forall ((xb Int)) 
    (=> (range n xb) 
      (exists ((xq Int)) (and (and (range n xq) (sLe (select q xq) ((_ map (- (Int Int) Int)) principles (select b xb)))) (not (nonCheck (select q xq))))))))

(define-fun availabilityP ((b QSystem) (q QSystem) (h QSlices)) Bool
  (forall ((xb Int)) 
    (=> (and (range n xb) (projection (select b xb) h)) 
      (exists ((xq Int)) 
        (and (and (and (projection (select q xq) h) (sLe (select q xq) ((_ map (- (Int Int) Int)) h (select b xb)))) (range n xq)) (not (nonCheck (select q xq))))))))

(define-fun sIntegrity ((q QSystem) (b QSystem)) Bool ; this function actually does not work for the union matrix.
  (forall ((i1 Int) (i2 Int) (i3 Int) (j Int)) 
    (=> (and (and (and (and (range n i1) (range n i2)) (and (range n i3) (range n j))) (not (= 0 (select (select q i1) j)))) (not (= 0 (select (select q i2) j))))
      (>
        (- (+ (select (select q i1) j) (select (q i2) j)) (select principles j)) (select (select b i3) j)
      ))))

(define-fun lableLe ((c1 Conf) (c2 Conf) (i1 QSystem) (i2 QSystem) (a1 QSystem) (a2 QSystem)) Bool ; the lable comparision l1 <= l2
  (and (and (cLe c1 c2) (bLe i2 i1)) (bLe a2 a1)))

; test one-time transfer constraints
(assert (= n 3)) ; three trust domains including client
(assert (iniQs principles))
(assert (and (= 4 (select principles 0)) (and (= 7 (select principles 1)) (= 1 (select principles 2))))); this may need to be changed,the elements in this set can have larger numbers 

; initialize the final type tau
(declare-const resultC Conf)
(declare-const resultI QSystem)
(declare-const resultA QSystem)
(declare-const qs1 QSlices)
(assert (iniQs qs1))
(assert (and (= 2 (select qs1 0)) (and (= 1 (select qs1 1)) (= 0 (select qs1 2)))))
;(assert (iniC resultC))
;(assert (and (= false (select resultC 0)) (and (= false (select resultC 1)) (= true (select resultC 2))))); C
;(assert (and (iniQ resultI) (iniQ resultA)))
;(assert (and (= qs1 (select resultI 0)) (and (= ((as const (Array Int Int)) 0) (select resultI 1)) (= ((as const (Array Int Int)) 0) (select resultI 2))))); P2(A) X P1(B)
;(assert (= resultA resultI))

; initialize the context type tau_0
;(declare-const startC Conf)
;(declare-const startI QSystem)
;(declare-const startA QSystem)
;(declare-const qs3 QSlices)
;(assert (iniQs qs3))
;(assert (and (= 4 (select qs3 0)) (and (= 7 (select qs3 1)) (= 0 (select qs3 2)))))
;(assert (iniC startC))
;(assert (and (= true (select startC 0)) (and (= true (select startC 1)) (= true (select startC 2))))); A, B, C
;(assert (and (= qs3 (select startI 0)) (and (= ((as const (Array Int Int)) 0) (select startI 1)) (= ((as const (Array Int Int)) 0) ;(select startI 2))))); P4(A) X P7(B)
;(assert (= startA resultI))

; initialize other user defined type variables
;(declare-const aC Conf)
;(declare-const bC Conf)
;(assert (and (iniC aC) (iniC bC)))
;(assert (and (= true (select aC 0)) (and (= false (select aC 1)) (= true (select aC 2))))); A, C
;(assert (and (= false (select bC 0)) (and (= true (select bC 1)) (= true (select bC 2))))); B, C

; initialize the type variables
;(declare-const m1conxtC Conf)
;(declare-const m1conxtI QSystem)
;(declare-const m1conxtA QSystem)
;(declare-const m2conxtC Conf)
;(declare-const m2conxtI QSystem)
;(declare-const m2conxtA QSystem)
;(declare-const r1readC Conf) ; the return type of r1 read
;(declare-const r1readI QSystem)  
;(declare-const r1readA QSystem)
;(declare-const r2readC Conf) ; the return type of r2 read
;(declare-const r2readI QSystem)
;(declare-const r2readA QSystem)
;(assert (iniC m1conxtC))
;(assert (iniC m2conxtC))
;(assert (iniC r1readC))
;(assert (iniC r2readC))
;(assert (iniQ m1conxtI))
;(assert (iniQ m1conxtA))
;(assert (iniQ m2conxtI))
;(assert (iniQ m2conxtA))
;(assert (iniQ r1readI))
;(assert (iniQ r2readI))
;(assert (iniQ r1readA))
;(assert (iniQ r2readA))

; initialize the placement information for methods
;(declare-const retQ QSystem) ; ret method hear from 
;(declare-const retH QSlices) ; ret method hosts
;(declare-const m1Q QSystem) ; m1 method hear from
;(declare-const m1H QSlices) ; m1 method hosts
;(declare-const m2Q QSystem) ; m2 method hear from
;(declare-const m2H QSlices) ; m2 method hosts
;(assert (iniQs retH))
;(assert (iniQs m1H))
;(assert (iniQs m2H))
;(assert (iniQ retQ))
;(assert (iniQ m1Q))
;(assert (iniQ m2Q))
; for objects
;(declare-const r1Q1 QSystem) ; r1 Q1 hosts
;(declare-const r1Q2 QSystem) ; r1 Q2 hear from
;(declare-const r2Q1 QSystem) ; r2 Q1 hosts
;(declare-const r2Q2 QSystem) ; r2 Q2 hear from
;(assert (iniQ r1Q1))
;(assert (iniQ r1Q2))
;(assert (iniQ r2Q1))
;(assert (iniQ r2Q2))

;for objects methods
;(declare-const r1inputC Conf) ; r1.read() input \tau_1
;(declare-const r1inputI QSystem)
;(declare-const r1inputA QSystem)
;(declare-const r2inputC Conf) ; r2.read() input \tau_1
;(declare-const r2inputI QSystem)
;(declare-const r2inputA QSystem)
;(assert (iniC r1inputC))
;(assert (iniC r2inputC))
;(assert (iniQ r1inputI))
;(assert (iniQ r2inputI))
;(assert (iniQ r1inputA))
;(assert (iniQ r2inputA))

; add constraints from m1->res and m2->res (ThisallT)
;(assert (lableLe r1readC resultC r1readI resultI r1readA resultA)) ; \tau <= \tau_1
;(assert (lableLe m1conxtC resultC m1conxtI resultI m1conxtA resultA)) ; \tau_x <= \tau_1
;(assert (availabilityP resultA retQ m1H)) ; a1 <= Availability(Q|H)
;(assert (lableLe r2readC resultC r2readI resultI r2readA resultA)) ; \tau <= \tau_1
;(assert (lableLe m2conxtC resultC m2conxtI resultI m2conxtA resultA)) ; \tau_x <= \tau_1
;(assert (availabilityP resultA retQ m2H)) ; a1 <= Availability(Q|H)

; ObjCallT (r1.read() r2.read())
;(assert (lableLe m1conxtC r1inputC m1conxtI r1inputI m1conxtA r1inputA)) ; \tau(\bot from conxt) <= \tau_1
;(assert (availabilityP r1inputA r1Q2 m1H)) ; a1 <= Availability(Q|H)
;(assert (lableLe m2conxtC r2inputC m2conxtI r2inputI m2conxtA r2inputA)) ; \tau(\bot from conxt) <= \tau_1
;(assert (availabilityP r2inputA r2Q2 m2H)) ; a1 <= Availability(Q|H)

; FieldT (r1, r2)
;(assert (confQ r1readC r1Q1)) ; c'm <= Q1
;(assert (sIntegrity r1Q1 r1readI)) ; i'm <= SIntegrity (Q1)
;(assert (availabilityC r1readA r1Q1)) ; a'm <= Availability(Q1)
;(assert (cIntegrity r1inputI r1Q2 m1H)) ; im <= CIntegrity(Q2)
;(assert (cLe aC r1readC))
;(assert (confQ r2readC r2Q1)) ; c'm <= Q1
;(assert (sIntegrity r2Q1 r2readI)) ; i'm <= SIntegrity (Q1)
;(assert (availabilityC r2readA r2Q1)) ; a'm <= Availability(Q1)
;(assert (cIntegrity r2inputI r2Q2 m2H)) ; im <= CIntegrity(Q2)
;(assert (cLe bC r2readC))

; MethodT (res(x'))
;(assert (cLeH resultC retH)) ; cx <= H
;(assert (cIntegrity resultI retQ m1H)) ; i1 <= CIntegrity(Q|H)
;(assert (cIntegrity resultI retQ m2H)) ; i1 <= CIntegrity(Q|H)

(check-sat)
(get-model)



