from z3 import *
import time

startT = time.time()
s = Optimize()

def solve(constr):
    s.add(constr)
    if s.check() == sat:
        return True
    else: 
        return False

def cLe(c1, c2):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(Implies(c2[i1], c1[i1])))
        constraints.append(Implies(c2[i1], c1[i1]))
    result = And(constraints)
    return result

def cLeH (c1, h):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(Implies(h[i1] > 0, c1[i1])))
        constraints.append(Implies(h[i1] > 0, c1[i1]))
    result = And(constraints)
    return result

def nonCheck(qs):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(qs[i1] == 0))
        constraints.append(qs[i1] == 0)
    result = And(constraints)
    return result

def nonCheckQ(q):
    constraints = []
    for i1 in range(n):
        for i2 in range(n):
            constraints.append(q[i1][i2] == 0)
    result = And(constraints)
    return result

def sLe(qs1, qs2):
    constraints = []
    for i1 in range(n):
        #constraints.append(And(qs2[i1] >= qs1[i1]))
        constraints.append(qs2[i1] >= qs1[i1])
    result = And(constraints)
    return result

def sL(qs1, qs2):
    constraints = []
    for i1 in range(n):
        constraints.append(qs1[i1] < qs2[i1])
    result = Implies(Not(nonCheck(qs2)), Or(constraints))
    return result

def bLe(q1, q2):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            subconstr.append(sLe(q1[i1], q2[i2]))   
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def confQ(c, q):
    constraints = []
    for i1 in range(n):
        for i2 in range(n):
            constraints.append(Implies(q[i1][i2] > 0, c[i2]))
    result = And(constraints)
    return result

#There are two solutions about representation of the Q matrix:
#1. the nonCheck can be eliminated because of integrity constraint. Then we will have to deal with redundency
#2. add nonCheck and then [[4, 7, 1],[0, 0, 0],[0, 0, 0]] can exists
def availabilityC(b, q):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            aliveQs = [ principals[i] - b[i1][i] for i in range(n) ]
            subconstr.append(And(sLe(q[i2], aliveQs), Not(nonCheck(q[i2]))))
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def availabilityP(b, q, h):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            for a in range(n):
                alive.append(Implies(h[a] > 0, q[i2][a] <= h[a] - b[i1][a]))
                alive.append(Implies(h[a] == 0, q[i2][a] <= 0))
                #alive.append(Implies(q[i2][a] >= 0, q[i2][a] <= h[a] - b[i1][a]))
            subconstr.append(And(sLe(q[i2], h), Not(nonCheck(q[i2])), And(alive)))
        constraints.append(Or(subconstr))
    result = And(constraints)
    return result

def cIntegrity(b, q):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            for a in range(n):
                #alive.append(Implies(a < n-1, b[i1][a] < q[i2][a]))
                alive.append(And(a < n-1, b[i1][a] < q[i2][a]))
            #subconstr.append(Implies(Not(nonCheck(q[i2])), And(alive)))
            subconstr.append(Implies(Not(nonCheck(q[i2])), Or(alive)))
        constraints.append(And(subconstr))
    result = And(constraints)
    return result

def cIntegrityE(b, q):
    constraints = []
    for i1 in range(n):
        subconstr = []
        for i2 in range(n):
            alive = []
            for a in range(n):
                alive.append(b[i1][a] < q[i2][a])
            subconstr.append(Implies(Not(nonCheck(q[i2])), Or(alive)))
        constraints.append(And(subconstr))
    result = And(constraints)
    return result

def sIntegrity(b, q, h):
    constraints = []
    for i3 in range(n):
        for i1 in range(n):
            for i2 in range(n):
                for j in range(n):
                    constraints.append(Implies(And(Not(q[i1][j] == 0), Not(q[i2][j] == 0)), 
                                               ((q[i1][j] + q[i2][j] - h[j]) > b[i3][j])))
    result = And(constraints)
    return result

def lableLe(c1, c2, i1, i2, a1, a2):
    return And(cLe(c1, c2), bLe(i2, i1), bLe(a2, a1))

n = 3
principals = [ 7, 4, 4]
xC = [ False, False, True ]
xCI = [ [ Int("xCI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
xCIrange0 = [ And(0 <= xCI[i][j]) for i in range(n) for j in range(n) ]
s.add(xCIrange0)
xCIrange1 = [And(sLe(xCI[i], principals)) for i in range(n)]
s.add(xCIrange1)
xCA = [ [ Int("xCA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
xCArange0 = [ And(0 <= xCA[i][j]) for i in range(n) for j in range(n) ]
s.add(xCArange0)
xCArange1 = [And(sLe(xCA[i], principals)) for i in range(n)]
s.add(xCArange1)
startC = [ True, True, True ]
startI = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
startA = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
botC = [ True, True, True ]
botI = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
botA = [[ 7, 4, 4], [ 0, 0, 0], [ 0, 0, 0] ]
resultC = [ False, False, True ]
resultI = [[ 2, 1, 1], [ 0, 0, 0], [ 0, 0, 0] ]
resultA = [[ 2, 1, 1], [ 0, 0, 0], [ 0, 0, 0] ]
resH = [ 0, 0, 4]
resQ = [ [ Int("resQ_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
s.add([ And(0 <= resQ[i][j]) for i in range(n) for j in range(n) ])
s.add([ And(sLe(resQ[i], principals)) for i in range(n) ])
m0H = [ Int('m0H_%s' % i) for i in range(n) ] 
m0Q = [ [ Int('m0Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtC = [ Bool('m0conxtC_%s' % i) for i in range(n) ]
m0conxtI = [ [ Int('m0conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0conxtA = [ [ Int('m0conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0xC = [ Bool('m0xC_%s' % i) for i in range(n) ]
m0xI = [ [ Int('m0xI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0xA = [ [ Int('m0xA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0temp1C = [ Bool('m0temp1C_%s' % i) for i in range(n) ]
m0temp1I = [ [ Int('m0temp1I_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0temp1A = [ [ Int('m0temp1A_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m0range0 = [ And(0 <= m0conxtI[i][j], 0 <= m0conxtA[i][j], 0 <= m0Q[i][j], 0 <= m0xI[i][j], 0 <= m0xA[i][j], 0 <= m0temp1I[i][j], 0 <= m0temp1A[i][j]) for i in range(n) for j in range(n) ]
s.add(m0range0)
m0range1 = [And(sLe(m0conxtI[i], principals), sLe(m0conxtA[i], principals), sLe(m0Q[i], principals), sLe(m0xI[i], principals), sLe(m0xA[i], principals), sLe(m0temp1I[i], principals), sLe(m0temp1A[i], principals)) for i in range(n)]
s.add(m0range1)
m0range2 = [And(0 <= m0H[i]) for i in range(n)]
s.add(m0range2)
s.add(sLe(m0H, principals))
s.add(Not(nonCheck(m0H)))
s.add(Not(nonCheckQ(m0Q)))
m1H = [ Int('m1H_%s' % i) for i in range(n) ] 
m1Q = [ [ Int('m1Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1conxtC = [ Bool('m1conxtC_%s' % i) for i in range(n) ]
m1conxtI = [ [ Int('m1conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1conxtA = [ [ Int('m1conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1xC = [ Bool('m1xC_%s' % i) for i in range(n) ]
m1xI = [ [ Int('m1xI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1xA = [ [ Int('m1xA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m1range0 = [ And(0 <= m1conxtI[i][j], 0 <= m1conxtA[i][j], 0 <= m1Q[i][j], 0 <= m1xI[i][j], 0 <= m1xA[i][j]) for i in range(n) for j in range(n) ]
s.add(m1range0)
m1range1 = [And(sLe(m1conxtI[i], principals), sLe(m1conxtA[i], principals), sLe(m1Q[i], principals), sLe(m1xI[i], principals), sLe(m1xA[i], principals)) for i in range(n)]
s.add(m1range1)
m1range2 = [And(0 <= m1H[i]) for i in range(n)]
s.add(m1range2)
s.add(sLe(m1H, principals))
s.add(Not(nonCheck(m1H)))
s.add(Not(nonCheckQ(m1Q)))
m2H = [ Int('m2H_%s' % i) for i in range(n) ] 
m2Q = [ [ Int('m2Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2conxtC = [ Bool('m2conxtC_%s' % i) for i in range(n) ]
m2conxtI = [ [ Int('m2conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2conxtA = [ [ Int('m2conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2xC = [ Bool('m2xC_%s' % i) for i in range(n) ]
m2xI = [ [ Int('m2xI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2xA = [ [ Int('m2xA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m2range0 = [ And(0 <= m2conxtI[i][j], 0 <= m2conxtA[i][j], 0 <= m2Q[i][j], 0 <= m2xI[i][j], 0 <= m2xA[i][j]) for i in range(n) for j in range(n) ]
s.add(m2range0)
m2range1 = [And(sLe(m2conxtI[i], principals), sLe(m2conxtA[i], principals), sLe(m2Q[i], principals), sLe(m2xI[i], principals), sLe(m2xA[i], principals)) for i in range(n)]
s.add(m2range1)
m2range2 = [And(0 <= m2H[i]) for i in range(n)]
s.add(m2range2)
s.add(sLe(m2H, principals))
s.add(Not(nonCheck(m2H)))
s.add(Not(nonCheckQ(m2Q)))
m3H = [ Int('m3H_%s' % i) for i in range(n) ] 
m3Q = [ [ Int('m3Q_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3conxtC = [ Bool('m3conxtC_%s' % i) for i in range(n) ]
m3conxtI = [ [ Int('m3conxtI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3conxtA = [ [ Int('m3conxtA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3xC = [ Bool('m3xC_%s' % i) for i in range(n) ]
m3xI = [ [ Int('m3xI_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3xA = [ [ Int('m3xA_%s_%s' % (i, j)) for j in range(n) ] for i in range(n) ]
m3range0 = [ And(0 <= m3conxtI[i][j], 0 <= m3conxtA[i][j], 0 <= m3Q[i][j], 0 <= m3xI[i][j], 0 <= m3xA[i][j]) for i in range(n) for j in range(n) ]
s.add(m3range0)
m3range1 = [And(sLe(m3conxtI[i], principals), sLe(m3conxtA[i], principals), sLe(m3Q[i], principals), sLe(m3xI[i], principals), sLe(m3xA[i], principals)) for i in range(n)]
s.add(m3range1)
m3range2 = [And(0 <= m3H[i]) for i in range(n)]
s.add(m3range2)
s.add(sLe(m3H, principals))
s.add(Not(nonCheck(m3H)))
s.add(Not(nonCheckQ(m3Q)))
aqs = [ [ Int("aqs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
aqc = [ [ Int("aqc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
aH = [ 0, 4, 0]
areadbotC = [ Bool('areadbotC_%s' % i) for i in range(n) ]
areadbotI = [ [ Int("areadbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
areadbotA = [ [ Int("areadbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
areadoutputC = [ Bool('areadoutputC_%s' % i) for i in range(n) ]
areadoutputI = [ [ Int("areadoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
areadoutputA = [ [ Int("areadoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
awriteinput0C = [ Bool('awriteinput0C_%s' % i) for i in range(n) ]
awriteinput0I = [ [ Int("awriteinput0I_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
awriteinput0A = [ [ Int("awriteinput0A_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
awriteoutputC = [ Bool('awriteoutputC_%s' % i) for i in range(n) ]
awriteoutputI = [ [ Int("awriteoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
awriteoutputA = [ [ Int("awriteoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
arange0 = [ And(0 <= aqs[i][j], 0 <= aqc[i][j], 0 <= areadoutputI[i][j], 0 <= areadoutputA[i][j], 0 <= areadbotI[i][j], 0 <= areadbotA[i][j], 0 <= awriteoutputI[i][j], 0 <= awriteoutputA[i][j], 0 <= awriteinput0I[i][j], 0 <= awriteinput0A[i][j]) for i in range(n) for j in range(n) ]
s.add(arange0)
arange1 = [And(sLe(aqs[i], principals), sLe(aqc[i], principals), sLe(areadoutputI[i], principals), sLe(areadoutputA[i], principals), sLe(areadbotI[i], principals), sLe(areadbotA[i], principals), sLe(awriteoutputI[i], principals), sLe(awriteoutputA[i], principals), sLe(awriteinput0I[i], principals), sLe(awriteinput0A[i], principals)) for i in range(n)]
s.add(arange1)
i1qs = [ [ Int("i1qs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i1qc = [ [ Int("i1qc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i1H = [ 7, 0, 0]
i1readbotC = [ Bool('i1readbotC_%s' % i) for i in range(n) ]
i1readbotI = [ [ Int("i1readbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i1readbotA = [ [ Int("i1readbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i1readoutputC = [ True, False, True ]
i1readoutputI = [ [ Int("i1readoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i1readoutputA = [ [ Int("i1readoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i1range0 = [ And(0 <= i1qs[i][j], 0 <= i1qc[i][j], 0 <= i1readoutputI[i][j], 0 <= i1readoutputA[i][j], 0 <= i1readbotI[i][j], 0 <= i1readbotA[i][j]) for i in range(n) for j in range(n) ]
s.add(i1range0)
i1range1 = [And(sLe(i1qs[i], principals), sLe(i1qc[i], principals), sLe(i1readoutputI[i], principals), sLe(i1readoutputA[i], principals), sLe(i1readbotI[i], principals), sLe(i1readbotA[i], principals)) for i in range(n)]
s.add(i1range1)
i2qs = [ [ Int("i2qs_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i2qc = [ [ Int("i2qc_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i2H = [ 0, 4, 0]
i2readbotC = [ Bool('i2readbotC_%s' % i) for i in range(n) ]
i2readbotI = [ [ Int("i2readbotI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i2readbotA = [ [ Int("i2readbotA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i2readoutputC = [ False, True, True ]
i2readoutputI = [ [ Int("i2readoutputI_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i2readoutputA = [ [ Int("i2readoutputA_%s_%s" % (i, j)) for j in range(n) ] for i in range(n) ]
i2range0 = [ And(0 <= i2qs[i][j], 0 <= i2qc[i][j], 0 <= i2readoutputI[i][j], 0 <= i2readoutputA[i][j], 0 <= i2readbotI[i][j], 0 <= i2readbotA[i][j]) for i in range(n) for j in range(n) ]
s.add(i2range0)
i2range1 = [And(sLe(i2qs[i], principals), sLe(i2qc[i], principals), sLe(i2readoutputI[i], principals), sLe(i2readoutputA[i], principals), sLe(i2readbotI[i], principals), sLe(i2readbotA[i], principals)) for i in range(n)]
s.add(i2range1)
#FieldT: a
s.add(confQ(areadoutputC, aqs))
s.add(sIntegrity(areadoutputI, aqs, aH))
s.add(availabilityP(areadoutputA, aqs, aH))
s.add(cIntegrityE(areadbotI, aqc))
s.add(lableLe(areadbotC, areadoutputC, areadbotI, areadoutputI, areadbotA, areadoutputA))
s.add(confQ(awriteoutputC, aqs))
s.add(sIntegrity(awriteoutputI, aqs, aH))
s.add(availabilityP(awriteoutputA, aqs, aH))
s.add(cIntegrityE(awriteinput0I, aqc))
s.add(lableLe(awriteinput0C, awriteoutputC, awriteinput0I, awriteoutputI, awriteinput0A, awriteoutputA))
#FieldT: i1
s.add(confQ(i1readoutputC, i1qs))
s.add(sIntegrity(i1readoutputI, i1qs, i1H))
s.add(availabilityP(i1readoutputA, i1qs, i1H))
s.add(cIntegrityE(i1readbotI, i1qc))
s.add(lableLe(i1readbotC, i1readoutputC, i1readbotI, i1readoutputI, i1readbotA, i1readoutputA))
#FieldT: i2
s.add(confQ(i2readoutputC, i2qs))
s.add(sIntegrity(i2readoutputI, i2qs, i2H))
s.add(availabilityP(i2readoutputA, i2qs, i2H))
s.add(cIntegrityE(i2readbotI, i2qc))
s.add(lableLe(i2readbotC, i2readoutputC, i2readbotI, i2readoutputI, i2readbotA, i2readoutputA))
#MethodT: m3
#ObjCallT: let x14 = a.read() in If (x14) then (let x12 = a.write(1) in let temp1 = i1.read() in let temp2 = i2.read() in If (x) then (this.ret(temp1)) else (this.ret(temp2))) else (this.ret(0))
s.add(cLeH(areadoutputC, m3H))
s.add(availabilityP(areadbotA, aqc, m3H))
#IfT: If (x14) then (this.m2(x)) else (this.ret(0))
#ThisCallT: this.m2(x)
s.add(cLe(m3conxtC, m2conxtC))
s.add(cLe(areadoutputC, m2conxtC))
s.add(cLe(botC, m2conxtC))
s.add(cLe(areadoutputC, m2conxtC))
s.add(cLe(botC, m2conxtC))
s.add(bLe(m2conxtI, m3conxtI))
s.add(bLe(m2conxtI, areadoutputI))
s.add(bLe(m2conxtI, botI))
s.add(bLe(m2conxtI, areadoutputI))
s.add(bLe(m2conxtI, botI))
s.add(bLe(m2conxtA, m3conxtA))
s.add(bLe(m2conxtA, areadoutputA))
s.add(bLe(m2conxtA, botA))
s.add(bLe(m2conxtA, areadoutputA))
s.add(bLe(m2conxtA, botA))
s.add(cLe(m3xC, m2xC))
s.add(cLe(xC, m2xC))
s.add(bLe(m2xI, m3xI))
s.add(bLe(m2xI, botI))
s.add(bLe(m2xA, m3xA))
s.add(bLe(m2xA, botA))
s.add(cLe(m3conxtC, m2xC))
s.add(cLe(areadoutputC, m2xC))
s.add(cLe(botC, m2xC))
s.add(cLe(areadoutputC, m2xC))
s.add(cLe(botC, m2xC))
s.add(bLe(m2xI, m3conxtI))
s.add(bLe(m2xI, areadoutputI))
s.add(bLe(m2xI, botI))
s.add(bLe(m2xI, areadoutputI))
s.add(bLe(m2xI, botI))
s.add(bLe(m2xA, m3conxtA))
s.add(bLe(m2xA, areadoutputA))
s.add(bLe(m2xA, botA))
s.add(bLe(m2xA, areadoutputA))
s.add(bLe(m2xA, botA))
s.add(availabilityP(m2xA, m2Q, m3H))
#ThisCallT: this.ret(0)
s.add(cLe(m3conxtC, resultC))
s.add(cLe(areadoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(areadoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m3conxtI))
s.add(bLe(resultI, areadoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, areadoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m3conxtA))
s.add(bLe(resultA, areadoutputA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, areadoutputA))
s.add(bLe(resultA, botA))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, botA))
s.add(cLe(m3conxtC, resultC))
s.add(cLe(areadoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(cLe(areadoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m3conxtI))
s.add(bLe(resultI, areadoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, areadoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m3conxtA))
s.add(bLe(resultA, areadoutputA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, areadoutputA))
s.add(bLe(resultA, botA))
s.add(availabilityP(resultA, resQ, m3H))
s.add(cIntegrityE(m3xI, m3Q))
s.add(cLeH(m3xC, m3H))
s.add(cLeH(m3conxtC, m3H))
#MethodT: m2
#ObjCallT: let x12 = a.write(1) in let temp1 = i1.read() in let temp2 = i2.read() in If (x) then (this.ret(temp1)) else (this.ret(temp2))
s.add(cLeH(awriteoutputC, m2H))
s.add(cLe(botC, awriteinput0C))
s.add(bLe(awriteinput0I, botI))
s.add(bLe(awriteinput0A, botA))
s.add(availabilityP(awriteinput0A, aqc, m2H))
#ThisCallT: this.m1(x)
s.add(cLe(m2conxtC, m1conxtC))
s.add(bLe(m1conxtI, m2conxtI))
s.add(bLe(m1conxtA, m2conxtA))
s.add(cLe(m2xC, m1xC))
s.add(cLe(xC, m1xC))
s.add(bLe(m1xI, m2xI))
s.add(bLe(m1xI, botI))
s.add(bLe(m1xA, m2xA))
s.add(bLe(m1xA, botA))
s.add(cLe(m2conxtC, m1xC))
s.add(bLe(m1xI, m2conxtI))
s.add(bLe(m1xA, m2conxtA))
s.add(availabilityP(m1xA, m1Q, m2H))
s.add(cIntegrityE(m2xI, m2Q))
s.add(cLeH(m2xC, m2H))
s.add(cLeH(m2conxtC, m2H))
#MethodT: m1
#ObjCallT: let temp1 = i1.read() in let temp2 = i2.read() in If (x) then (this.ret(temp1)) else (this.ret(temp2))
s.add(cLeH(i1readoutputC, m1H))
s.add(availabilityP(i1readbotA, i1qc, m1H))
#ThisCallT: this.m0(x, temp1)
s.add(cLe(m1conxtC, m0conxtC))
s.add(bLe(m0conxtI, m1conxtI))
s.add(bLe(m0conxtA, m1conxtA))
s.add(cLe(m1xC, m0xC))
s.add(cLe(xC, m0xC))
s.add(bLe(m0xI, m1xI))
s.add(bLe(m0xI, botI))
s.add(bLe(m0xA, m1xA))
s.add(bLe(m0xA, botA))
s.add(cLe(m1conxtC, m0xC))
s.add(bLe(m0xI, m1conxtI))
s.add(bLe(m0xA, m1conxtA))
s.add(availabilityP(m0xA, m0Q, m1H))
s.add(cLe(i1readoutputC, m0temp1C))
s.add(cLe(botC, m0temp1C))
s.add(bLe(m0temp1I, i1readoutputI))
s.add(bLe(m0temp1I, botI))
s.add(bLe(m0temp1A, i1readoutputA))
s.add(bLe(m0temp1A, botA))
s.add(cLe(m1conxtC, m0temp1C))
s.add(bLe(m0temp1I, m1conxtI))
s.add(bLe(m0temp1A, m1conxtA))
s.add(availabilityP(m0temp1A, m0Q, m1H))
s.add(cIntegrityE(m1xI, m1Q))
s.add(cLeH(m1xC, m1H))
s.add(cLeH(m1conxtC, m1H))
#MethodT: m0
#ObjCallT: let temp2 = i2.read() in If (x) then (this.ret(temp1)) else (this.ret(temp2))
s.add(cLeH(i2readoutputC, m0H))
s.add(availabilityP(i2readbotA, i2qc, m0H))
#IfT: If (x) then (this.ret(temp1)) else (this.ret(temp2))
#ThisCallT: this.ret(temp1)
s.add(cLe(m0conxtC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m0conxtA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(cLe(m0temp1C, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, m0temp1I))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m0temp1A))
s.add(bLe(resultA, botA))
s.add(cLe(m0conxtC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m0conxtA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(availabilityP(resultA, resQ, m0H))
#ThisCallT: this.ret(temp2)
s.add(cLe(m0conxtC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m0conxtA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(cLe(i2readoutputC, resultC))
s.add(cLe(botC, resultC))
s.add(bLe(resultI, i2readoutputI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, i2readoutputA))
s.add(bLe(resultA, botA))
s.add(cLe(m0conxtC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(cLe(m0xC, resultC))
s.add(cLe(xC, resultC))
s.add(bLe(resultI, m0conxtI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultI, m0xI))
s.add(bLe(resultI, botI))
s.add(bLe(resultA, m0conxtA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(bLe(resultA, m0xA))
s.add(bLe(resultA, botA))
s.add(availabilityP(resultA, resQ, m0H))
s.add(cIntegrityE(m0xI, m0Q))
s.add(cIntegrityE(m0temp1I, m0Q))
s.add(cLeH(m0xC, m0H))
s.add(cLeH(m0temp1C, m0H))
s.add(cLeH(m0conxtC, m0H))
#MethodT: ret
s.add(cLeH(resultC, resH))
s.add(cIntegrityE(resultI, resQ))
s.add(cLe(startC, m3conxtC))
s.add(bLe(m3conxtI, startI))
s.add(bLe(m3conxtA, startA))
s.add(cLe(startC, m3xC))
s.add(bLe(m3xI, startI))
s.add(bLe(m3xA, startA))
s.add(cLe(xC, m3xC))
s.add(bLe(m3xI, botI))
s.add(bLe(m3xA, botA))
s.add(availabilityP(m3xA, m3Q, resH))
print("n = 3")
print("principals = [ 7, 4, 4]")
weight = [ 1, 1, 12]

s.minimize(sum(m0H[i] * weight[i] for i in range(n)) + sum(m1H[i] * weight[i] for i in range(n)) + sum(m2H[i] * weight[i] for i in range(n)) + sum(m3H[i] * weight[i] for i in range(n)) + sum(aH[i] * weight[i] for i in range(n)) + sum(i1H[i] * weight[i] for i in range(n)) + sum(i2H[i] * weight[i] for i in range(n)) + sum(aqs[0][i] * weight[i] for i in range(n)) + sum(aqs[1][i] * weight[i] for i in range(n)) + sum(aqs[2][i] * weight[i] for i in range(n)) + sum(i1qs[0][i] * weight[i] for i in range(n)) + sum(i1qs[1][i] * weight[i] for i in range(n)) + sum(i1qs[2][i] * weight[i] for i in range(n)) + sum(i2qs[0][i] * weight[i] for i in range(n)) + sum(i2qs[1][i] * weight[i] for i in range(n)) + sum(i2qs[2][i] * weight[i] for i in range(n)) + sum(aqc[0][i] * weight[i] for i in range(n)) + sum(aqc[1][i] * weight[i] for i in range(n)) + sum(aqc[2][i] * weight[i] for i in range(n)) + sum(i1qc[0][i] * weight[i] for i in range(n)) + sum(i1qc[1][i] * weight[i] for i in range(n)) + sum(i1qc[2][i] * weight[i] for i in range(n)) + sum(i2qc[0][i] * weight[i] for i in range(n)) + sum(i2qc[1][i] * weight[i] for i in range(n)) + sum(i2qc[2][i] * weight[i] for i in range(n)) + sum(resQ[0]) + sum(resQ[1]) + sum(resQ[2]) + sum(m0Q[0]) + sum(m0Q[1]) + sum(m0Q[2]) + sum(m1Q[0]) + sum(m1Q[1]) + sum(m1Q[2]) + sum(m2Q[0]) + sum(m2Q[1]) + sum(m2Q[2]) + sum(m3Q[0]) + sum(m3Q[1]) + sum(m3Q[2]))
print(s.check())
m = s.model()
print("resH:")
print(resH)
print("m0H:")
print([m[hInfo].as_long() for hInfo in m0H])
print("m1H:")
print([m[hInfo].as_long() for hInfo in m1H])
print("m2H:")
print([m[hInfo].as_long() for hInfo in m2H])
print("m3H:")
print([m[hInfo].as_long() for hInfo in m3H])
print("resQ:")
print([m[e].as_long() for qs in resQ for e in qs])
print("m0Q:")
print([m[e].as_long() for qs in m0Q for e in qs])
print("m1Q:")
print([m[e].as_long() for qs in m1Q for e in qs])
print("m2Q:")
print([m[e].as_long() for qs in m2Q for e in qs])
print("m3Q:")
print([m[e].as_long() for qs in m3Q for e in qs])
print("aqs:")
print([m[e].as_long() for qs in aqs for e in qs])
print("i1qs:")
print([m[e].as_long() for qs in i1qs for e in qs])
print("i2qs:")
print([m[e].as_long() for qs in i2qs for e in qs])
print("aqc:")
print([m[e].as_long() for qs in aqc for e in qs])
print("i1qc:")
print([m[e].as_long() for qs in i1qc for e in qs])
print("i2qc:")
print([m[e].as_long() for qs in i2qc for e in qs])
#print("aH:")
#print([m[hInfo].as_long() for hInfo in aH])
#print("i1H:")
#print([m[hInfo].as_long() for hInfo in i1H])
#print("i2H:")
#print([m[hInfo].as_long() for hInfo in i2H])
endT = time.time() - startT
print(endT)
