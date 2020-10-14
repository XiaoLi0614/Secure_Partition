#include <DoubleMatrix.h>


#include "DoubleMatrix.inc"


//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.PropertyDecl_c

//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.PropertyDecl_c
void DoubleMatrix::_instance_init() {
    _I_("Doing initialisation for class: DoubleMatrix");
    
}


//#line 10 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10FieldDecl_c

//#line 14 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10ConstructorDecl_c
void DoubleMatrix::_constructor(x10aux::ref<x10::array::Array<x10_double> > array)
{
    this->x10::lang::Object::_constructor();
    
    //#line 17 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int length =
      ((x10_int) ((((x10_int) ((x10aux::nullCheck(array)->
                                  FMGL(region)->max(
                                  ((x10_int)0))) - (x10aux::nullCheck(array)->
                                                      FMGL(region)->min(
                                                      ((x10_int)0)))))) + (((x10_int)1))));
    {
        
        //#line 18 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.AssignPropertyCall_c
        FMGL(n) = ((x10_int)1);
        FMGL(m) = length;
        
        //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        ((x10aux::ref<DoubleMatrix>)this)->DoubleMatrix::__fieldInitializers110();
    }
    
    //#line 19 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r =
      x10aux::nullCheck((x10::array::Region::makeRectangular(
                           ((x10_int)0),
                           ((x10_int)0))))->__times(
        x10::array::Region::makeRectangular(
          ((x10_int)0),
          ((x10_int) ((length) - (((x10_int)1))))));
    
    //#line 20 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix>)this)->
      FMGL(array) =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__0>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__0)))DoubleMatrix__closure__0(array)))));
    
}
x10aux::ref<DoubleMatrix> DoubleMatrix::_make(
  x10aux::ref<x10::array::Array<x10_double> > array)
{
    x10aux::ref<DoubleMatrix> this_ = new (memset(x10aux::alloc<DoubleMatrix>(), 0, sizeof(DoubleMatrix))) DoubleMatrix();
    this_->_constructor(array);
    return this_;
}



//#line 28 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10ConstructorDecl_c
void DoubleMatrix::_constructor(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > arrays)
{
    this->x10::lang::Object::_constructor();
    
    //#line 29 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int nv =
      ((x10_int) ((((x10_int) ((x10aux::nullCheck(arrays)->
                                  FMGL(region)->max(
                                  ((x10_int)0))) - (x10aux::nullCheck(arrays)->
                                                      FMGL(region)->min(
                                                      ((x10_int)0)))))) + (((x10_int)1))));
    
    //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > fArr =
      (__extension__ ({
        x10aux::ref<x10::array::Array<x10_double> > __desugarer__var__2__ =
          x10aux::nullCheck(arrays)->x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > >::apply(
            ((x10_int)0));
        
        //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
        x10aux::ref<x10::array::Array<x10_double> > __var40__;
        
        //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Labeled_c
        goto __ret138; __ret138: 
        //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Do_c
        do
        {
        {
            
            //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(__desugarer__var__2__,
                                        x10aux::null)) &&
                !((x10aux::struct_equals(x10aux::nullCheck(__desugarer__var__2__)->
                                           FMGL(region)->
                                           FMGL(rank),
                                         ((x10_int)1)))))
            {
                
                //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Throw_c
                x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.array.Array[x10.lang.Double]{self.region.rank==1}"))));
            }
            
            //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            __var40__ =
              __desugarer__var__2__;
            
            //#line 30 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Branch_c
            goto __ret138_end_;
        }
        goto __ret138_next_; __ret138_next_: ;
        }
        while (false);
        goto __ret138_end_; __ret138_end_: ;
        __var40__;
    }))
    ;
    
    //#line 31 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int mv =
      ((x10_int) ((((x10_int) ((x10aux::nullCheck(fArr)->
                                  FMGL(region)->max(
                                  ((x10_int)0))) - (x10aux::nullCheck(fArr)->
                                                      FMGL(region)->min(
                                                      ((x10_int)0)))))) + (((x10_int)1))));
    {
        
        //#line 32 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.AssignPropertyCall_c
        FMGL(n) = nv;
        FMGL(m) = mv;
        
        //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        ((x10aux::ref<DoubleMatrix>)this)->DoubleMatrix::__fieldInitializers110();
    }
    
    //#line 39 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r =
      x10aux::nullCheck((x10::array::Region::makeRectangular(
                           ((x10_int)0),
                           ((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                                          FMGL(n)) - (((x10_int)1)))))))->__times(
        x10::array::Region::makeRectangular(
          ((x10_int)0),
          ((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                         FMGL(m)) - (((x10_int)1))))));
    
    //#line 40 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix>)this)->
      FMGL(array) =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__1>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__1)))DoubleMatrix__closure__1(arrays)))));
    
}
x10aux::ref<DoubleMatrix> DoubleMatrix::_make(
  x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > arrays)
{
    x10aux::ref<DoubleMatrix> this_ = new (memset(x10aux::alloc<DoubleMatrix>(), 0, sizeof(DoubleMatrix))) DoubleMatrix();
    this_->_constructor(arrays);
    return this_;
}



//#line 53 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10ConstructorDecl_c
void DoubleMatrix::_constructor(x10aux::ref<x10::array::Array<x10_double> > array,
                                x10_int nv,
                                x10_int mv)
{
    this->x10::lang::Object::_constructor();
    {
        
        //#line 54 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.AssignPropertyCall_c
        FMGL(n) = nv;
        FMGL(m) = mv;
        
        //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        ((x10aux::ref<DoubleMatrix>)this)->DoubleMatrix::__fieldInitializers110();
    }
    
    //#line 55 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix>)this)->
      FMGL(array) =
      array;
    
}
x10aux::ref<DoubleMatrix> DoubleMatrix::_make(
  x10aux::ref<x10::array::Array<x10_double> > array,
  x10_int nv,
  x10_int mv)
{
    x10aux::ref<DoubleMatrix> this_ = new (memset(x10aux::alloc<DoubleMatrix>(), 0, sizeof(DoubleMatrix))) DoubleMatrix();
    this_->_constructor(array,
    nv,
    mv);
    return this_;
}



//#line 60 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_double DoubleMatrix::apply(x10_int i,
                               x10_int j) {
    
    //#line 61 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                               FMGL(array))->x10::array::Array<x10_double>::apply(
             ((x10_int) ((i) - (((x10_int)1)))),
             ((x10_int) ((j) - (((x10_int)1)))));
    
}

//#line 64 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::apply(
  x10aux::ref<IntMatrix> i,
  x10aux::ref<IntMatrix> j) {
    
    //#line 65 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(i)->FMGL(m);
    
    //#line 66 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(j)->FMGL(m);
    
    //#line 68 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 69 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__2>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__2)))DoubleMatrix__closure__2(this, i, j)))));
    
    //#line 80 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               rm);
    
}

//#line 83 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::apply(
  x10_int i,
  x10aux::ref<IntMatrix> j) {
    
    //#line 84 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(j)->FMGL(m);
    
    //#line 86 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int)1))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 87 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__3>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__3)))DoubleMatrix__closure__3(this, i, j)))));
    
    //#line 98 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, ((x10_int)1),
                               rm);
    
}

//#line 101 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::apply(
  x10aux::ref<IntMatrix> i,
  x10_int j) {
    
    //#line 102 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(i)->FMGL(m);
    
    //#line 104 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int)1)));
    
    //#line 105 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__4>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__4)))DoubleMatrix__closure__4(this, i, j)))));
    
    //#line 116 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               ((x10_int)1));
    
}

//#line 119 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::apply(
  x10aux::ref<IntMatrix> index) {
    
    //#line 120 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                 FMGL(n),
                               ((x10_int)1))))
    {
        
        //#line 121 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(x10aux::nullCheck(index)->
                                     FMGL(n),
                                   ((x10_int)1))))
        {
            
            //#line 122 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
            return ((x10aux::ref<DoubleMatrix>)this)->applyHH(
                     index);
            
        }
        else
        
        //#line 123 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(x10aux::nullCheck(index)->
                                     FMGL(m),
                                   ((x10_int)1))))
        {
            
            //#line 124 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
            return ((x10aux::ref<DoubleMatrix>)this)->applyHV(
                     index);
            
        }
        else
        {
            
            //#line 126 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
            return ((x10aux::ref<DoubleMatrix>)this)->applyHM(
                     index);
            
        }
        
    }
    else
    
    //#line 127 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                 FMGL(m),
                               ((x10_int)1))))
    {
        
        //#line 128 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(x10aux::nullCheck(index)->
                                     FMGL(n),
                                   ((x10_int)1))))
        {
            
            //#line 129 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
            return ((x10aux::ref<DoubleMatrix>)this)->applyVH(
                     index);
            
        }
        else
        
        //#line 130 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
        if ((x10aux::struct_equals(x10aux::nullCheck(index)->
                                     FMGL(m),
                                   ((x10_int)1))))
        {
            
            //#line 131 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
            return ((x10aux::ref<DoubleMatrix>)this)->applyVV(
                     index);
            
        }
        else
        {
            
            //#line 133 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
            return ((x10aux::ref<DoubleMatrix>)this)->applyVM(
                     index);
            
        }
        
    }
    else
    {
        
        //#line 135 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return ((x10aux::ref<DoubleMatrix>)this)->applyMM(
                 index);
        
    }
    
}

//#line 138 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyHH(
  x10aux::ref<IntMatrix> index) {
    
    //#line 139 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(index)->
                   FMGL(m);
    
    //#line 141 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int)1))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 142 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__5>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__5)))DoubleMatrix__closure__5(index, this)))));
    
    //#line 157 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, ((x10_int)1),
                               rm);
    
}

//#line 159 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyHV(
  x10aux::ref<IntMatrix> index) {
    
    //#line 160 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(index)->
                   FMGL(n);
    
    //#line 162 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int)1))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 163 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__6>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__6)))DoubleMatrix__closure__6(index, this)))));
    
    //#line 178 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, ((x10_int)1),
                               rm);
    
}

//#line 180 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyVH(
  x10aux::ref<IntMatrix> index) {
    
    //#line 181 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(index)->
                   FMGL(m);
    
    //#line 183 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int)1)));
    
    //#line 184 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__7>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__7)))DoubleMatrix__closure__7(index, this)))));
    
    //#line 199 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               ((x10_int)1));
    
}

//#line 201 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyVV(
  x10aux::ref<IntMatrix> index) {
    
    //#line 202 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(index)->
                   FMGL(n);
    
    //#line 204 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int)1)));
    
    //#line 205 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__8>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__8)))DoubleMatrix__closure__8(index, this)))));
    
    //#line 220 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               ((x10_int)1));
    
}

//#line 222 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyHM(
  x10aux::ref<IntMatrix> index) {
    
    //#line 223 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(index)->
                   FMGL(n);
    
    //#line 224 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(index)->
                   FMGL(m);
    
    //#line 226 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 227 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__9>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__9)))DoubleMatrix__closure__9(index, this)))));
    
    //#line 242 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               rm);
    
}

//#line 244 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyVM(
  x10aux::ref<IntMatrix> index) {
    
    //#line 245 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(index)->
                   FMGL(n);
    
    //#line 246 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(index)->
                   FMGL(m);
    
    //#line 248 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 249 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__10>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__10)))DoubleMatrix__closure__10(index, this)))));
    
    //#line 264 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               rm);
    
}

//#line 266 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::applyMM(
  x10aux::ref<IntMatrix> index) {
    
    //#line 267 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rn = x10aux::nullCheck(index)->
                   FMGL(n);
    
    //#line 268 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rm = x10aux::nullCheck(index)->
                   FMGL(m);
    
    //#line 270 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((rn) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((rm) - (((x10_int)1))))));
    
    //#line 271 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__11>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__11)))DoubleMatrix__closure__11(index, this)))));
    
    //#line 289 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, rn,
                               rm);
    
}

//#line 292 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_double DoubleMatrix::apply(x10_int li) {
    
    //#line 293 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int rowCount = ((x10aux::ref<DoubleMatrix>)this)->
                         FMGL(n);
    
    //#line 294 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int columnCount = ((x10aux::ref<DoubleMatrix>)this)->
                            FMGL(m);
    
    //#line 295 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((((li) > (((x10_int) ((rowCount) * (columnCount)))))) ||
        (((li) < (((x10_int)1))))) {
        
        //#line 296 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(x10aux::string_utils::lit("At linear indexing assignment: Out of bounds linear index."));
    }
    
    //#line 297 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int i = ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) % (((x10aux::ref<DoubleMatrix>)this)->
                                                                       FMGL(n))));
    
    //#line 298 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int j = ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) / (((x10aux::ref<DoubleMatrix>)this)->
                                                                       FMGL(n))));
    
    //#line 299 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                               FMGL(array))->x10::array::Array<x10_double>::apply(
             i,
             j);
    
}

//#line 303 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::apply(
  x10aux::ref<BooleanMatrix> index) {
    
    //#line 304 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                   FMGL(n),
                                 x10aux::nullCheck(index)->
                                   FMGL(n)))) ||
        ((!x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                   FMGL(m),
                                 x10aux::nullCheck(index)->
                                   FMGL(m)))))
    {
        
        //#line 305 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At logical indexing: Mismatch of array and index dimensions."));
    }
    
    //#line 307 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::util::ArrayList<x10_double> > list =
      x10::util::ArrayList<x10_double>::_make();
    
    //#line 308 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int j;
        for (
             //#line 308 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             j = ((x10_int)0); ((j) < (((x10aux::ref<DoubleMatrix>)this)->
                                         FMGL(m)));
             
             //#line 308 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             j =
               ((x10_int) ((j) + (((x10_int)1)))))
        {
            
            //#line 309 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int i;
                for (
                     //#line 309 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     i =
                       ((x10_int)0);
                     ((i) < (((x10aux::ref<DoubleMatrix>)this)->
                               FMGL(n)));
                     
                     //#line 309 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     i =
                       ((x10_int) ((i) + (((x10_int)1)))))
                {
                    
                    //#line 310 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
                    if (x10aux::nullCheck(x10aux::nullCheck(index)->
                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                          i,
                          j))
                    {
                        
                        //#line 311 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                        list->add(
                          x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                            i,
                            j));
                    }
                    
                }
            }
            
        }
    }
    
    //#line 314 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int size = list->size();
    
    //#line 315 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((size) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int)1)));
    
    //#line 316 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__12>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__12)))DoubleMatrix__closure__12(list)))));
    
    //#line 325 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, size,
                               ((x10_int)1));
    
}

//#line 329 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::singleColumn(
  ) {
    
    //#line 330 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                                                                                         FMGL(n)) * (((x10aux::ref<DoubleMatrix>)this)->
                                                                                                       FMGL(m))))) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int)1)));
    
    //#line 331 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > array =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__13>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__13)))DoubleMatrix__closure__13(this)))));
    
    //#line 342 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(array, ((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                                                     FMGL(n)) * (((x10aux::ref<DoubleMatrix>)this)->
                                                                   FMGL(m)))),
                               ((x10_int)1));
    
}

//#line 349 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10_double v, x10_int i,
                       x10_int j) {
    
    //#line 350 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                        FMGL(array))->x10::array::Array<x10_double>::set(
      v,
      ((x10_int) ((i) - (((x10_int)1)))),
      ((x10_int) ((j) - (((x10_int)1)))));
}

//#line 354 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10_double v, x10aux::ref<IntMatrix> index1,
                       x10aux::ref<IntMatrix> index2) {
    
    //#line 355 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(x10aux::nullCheck(index1)->
                                   FMGL(n),
                                 ((x10_int)1)))) ||
        ((!x10aux::struct_equals(x10aux::nullCheck(index2)->
                                   FMGL(n),
                                 ((x10_int)1)))))
    {
        
        //#line 356 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Index arrays should be horizontal."));
    }
    
    //#line 358 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 358 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(index1)->
                                         FMGL(m)));
             
             //#line 358 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 359 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int j;
                for (
                     //#line 359 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     j =
                       ((x10_int)0);
                     ((j) < (x10aux::nullCheck(index2)->
                               FMGL(m)));
                     
                     //#line 359 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     j =
                       ((x10_int) ((j) + (((x10_int)1)))))
                {
                    
                    //#line 360 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                    x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                        FMGL(array))->x10::array::Array<x10_double>::set(
                      v,
                      ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index1)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     ((x10_int)0),
                                     i)) - (((x10_int)1)))),
                      ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index2)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     ((x10_int)0),
                                     j)) - (((x10_int)1)))));
                }
            }
            
        }
    }
    
}

//#line 363 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10aux::ref<DoubleMatrix> v,
                       x10aux::ref<IntMatrix> index1,
                       x10aux::ref<IntMatrix> index2) {
    
    //#line 364 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(x10aux::nullCheck(index1)->
                                   FMGL(n),
                                 ((x10_int)1)))) ||
        ((!x10aux::struct_equals(x10aux::nullCheck(index2)->
                                   FMGL(n),
                                 ((x10_int)1)))))
    {
        
        //#line 365 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Index arrays should be horizontal."));
    }
    
    //#line 366 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                   FMGL(n),
                                 x10aux::nullCheck(index1)->
                                   FMGL(m)))) ||
        ((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                   FMGL(m),
                                 x10aux::nullCheck(index2)->
                                   FMGL(m)))))
    {
        
        //#line 367 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Dimensions mismatch of indices and right hand side."));
    }
    
    //#line 368 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 368 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(index1)->
                                         FMGL(m)));
             
             //#line 368 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 369 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int j;
                for (
                     //#line 369 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     j =
                       ((x10_int)0);
                     ((j) < (x10aux::nullCheck(index2)->
                               FMGL(m)));
                     
                     //#line 369 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     j =
                       ((x10_int) ((j) + (((x10_int)1)))))
                {
                    
                    //#line 370 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                    x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                        FMGL(array))->x10::array::Array<x10_double>::set(
                      x10aux::nullCheck(x10aux::nullCheck(v)->
                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                        i,
                        j),
                      ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index1)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     ((x10_int)0),
                                     i)) - (((x10_int)1)))),
                      ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index2)->
                                                       FMGL(array))->x10::array::Array<x10_int>::apply(
                                     ((x10_int)0),
                                     j)) - (((x10_int)1)))));
                }
            }
            
        }
    }
    
}

//#line 374 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10_double v, x10_int index1,
                       x10aux::ref<IntMatrix> index2) {
    
    //#line 375 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(x10aux::nullCheck(index2)->
                                  FMGL(n),
                                ((x10_int)1))))
    {
        
        //#line 376 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Index arrays should be horizontal."));
    }
    
    //#line 377 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int j;
        for (
             //#line 377 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             j = ((x10_int)0); ((j) < (x10aux::nullCheck(index2)->
                                         FMGL(m)));
             
             //#line 377 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             j =
               ((x10_int) ((j) + (((x10_int)1)))))
        {
            
            //#line 378 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                FMGL(array))->x10::array::Array<x10_double>::set(
              v,
              ((x10_int) ((index1) - (((x10_int)1)))),
              ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index2)->
                                               FMGL(array))->x10::array::Array<x10_int>::apply(
                             ((x10_int)0),
                             j)) - (((x10_int)1)))));
        }
    }
    
}

//#line 380 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10aux::ref<DoubleMatrix> v,
                       x10_int index1,
                       x10aux::ref<IntMatrix> index2) {
    
    //#line 381 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(x10aux::nullCheck(index2)->
                                  FMGL(n),
                                ((x10_int)1))))
    {
        
        //#line 382 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Index arrays should be horizontal."));
    }
    
    //#line 383 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                   FMGL(n),
                                 ((x10_int)1)))) ||
        ((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                   FMGL(m),
                                 x10aux::nullCheck(index2)->
                                   FMGL(m)))))
    {
        
        //#line 384 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Dimensions mismatch of indices and right hand side."));
    }
    
    //#line 386 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int j;
        for (
             //#line 386 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             j = ((x10_int)0); ((j) < (x10aux::nullCheck(index2)->
                                         FMGL(m)));
             
             //#line 386 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             j =
               ((x10_int) ((j) + (((x10_int)1)))))
        {
            
            //#line 387 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                FMGL(array))->x10::array::Array<x10_double>::set(
              x10aux::nullCheck(x10aux::nullCheck(v)->
                                  FMGL(array))->x10::array::Array<x10_double>::apply(
                ((x10_int)0),
                j),
              ((x10_int) ((index1) - (((x10_int)1)))),
              ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index2)->
                                               FMGL(array))->x10::array::Array<x10_int>::apply(
                             ((x10_int)0),
                             j)) - (((x10_int)1)))));
        }
    }
    
}

//#line 390 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10_double v, x10aux::ref<IntMatrix> index1,
                       x10_int index2) {
    
    //#line 391 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(x10aux::nullCheck(index1)->
                                  FMGL(n),
                                ((x10_int)1))))
    {
        
        //#line 392 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Index arrays should be horizontal."));
    }
    
    //#line 393 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 393 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(index1)->
                                         FMGL(m)));
             
             //#line 393 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 394 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                FMGL(array))->x10::array::Array<x10_double>::set(
              v,
              ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index1)->
                                               FMGL(array))->x10::array::Array<x10_int>::apply(
                             ((x10_int)0),
                             i)) - (((x10_int)1)))),
              ((x10_int) ((index2) - (((x10_int)1)))));
        }
    }
    
}

//#line 396 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10aux::ref<DoubleMatrix> v,
                       x10aux::ref<IntMatrix> index1,
                       x10_int index2) {
    
    //#line 397 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(x10aux::nullCheck(index1)->
                                  FMGL(n),
                                ((x10_int)1))))
    {
        
        //#line 398 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Index arrays should be horizontal."));
    }
    
    //#line 399 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                   FMGL(n),
                                 x10aux::nullCheck(index1)->
                                   FMGL(m)))) ||
        ((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                   FMGL(m),
                                 ((x10_int)1)))))
    {
        
        //#line 400 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At multi indexing assignment: Dimensions mismatch of indices and right hand side."));
    }
    
    //#line 402 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 402 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(index1)->
                                         FMGL(m)));
             
             //#line 402 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 403 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
            x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                FMGL(array))->x10::array::Array<x10_double>::set(
              x10aux::nullCheck(x10aux::nullCheck(v)->
                                  FMGL(array))->x10::array::Array<x10_double>::apply(
                i,
                ((x10_int)0)),
              ((x10_int) ((x10aux::nullCheck(x10aux::nullCheck(index1)->
                                               FMGL(array))->x10::array::Array<x10_int>::apply(
                             ((x10_int)0),
                             i)) - (((x10_int)1)))),
              ((x10_int) ((index2) - (((x10_int)1)))));
        }
    }
    
}

//#line 408 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10_double v, x10aux::ref<IntMatrix> index) {
    
    //#line 409 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 409 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(index)->
                                         FMGL(n)));
             
             //#line 409 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 410 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int j;
                for (
                     //#line 410 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     j =
                       ((x10_int)0);
                     ((j) < (x10aux::nullCheck(index)->
                               FMGL(m)));
                     
                     //#line 410 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     j =
                       ((x10_int) ((j) + (((x10_int)1)))))
                {
                    
                    //#line 411 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int li =
                      x10aux::nullCheck(x10aux::nullCheck(index)->
                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                        i,
                        j);
                    
                    //#line 412 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int rowCount =
                      ((x10aux::ref<DoubleMatrix>)this)->
                        FMGL(n);
                    
                    //#line 413 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int columnCount =
                      ((x10aux::ref<DoubleMatrix>)this)->
                        FMGL(m);
                    
                    //#line 414 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
                    if ((((li) > (((x10_int) ((rowCount) * (columnCount)))))) ||
                        (((li) < (((x10_int)1)))))
                    {
                        
                        //#line 415 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                        DoubleMatrix::error(
                          x10aux::string_utils::lit("At linear indexing assignment: Out of bounds linear index."));
                    }
                    
                    //#line 416 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int ii =
                      ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) % (rowCount)));
                    
                    //#line 417 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int jj =
                      ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) / (rowCount)));
                    
                    //#line 418 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                    x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                        FMGL(array))->x10::array::Array<x10_double>::set(
                      v,
                      ii,
                      jj);
                }
            }
            
        }
    }
    
}

//#line 421 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10aux::ref<DoubleMatrix> v,
                       x10aux::ref<IntMatrix> index) {
    
    //#line 422 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(x10aux::nullCheck(index)->
                                   FMGL(n),
                                 x10aux::nullCheck(v)->
                                   FMGL(n)))) ||
        ((!x10aux::struct_equals(x10aux::nullCheck(index)->
                                   FMGL(m),
                                 x10aux::nullCheck(v)->
                                   FMGL(m)))))
    {
        
        //#line 423 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At linear indexing assignment: Dimensions mismatch of index and right hand side."));
    }
    
    //#line 424 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 424 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (x10aux::nullCheck(index)->
                                         FMGL(n)));
             
             //#line 424 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 425 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int j;
                for (
                     //#line 425 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     j =
                       ((x10_int)0);
                     ((j) < (x10aux::nullCheck(index)->
                               FMGL(m)));
                     
                     //#line 425 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     j =
                       ((x10_int) ((j) + (((x10_int)1)))))
                {
                    
                    //#line 426 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int li =
                      x10aux::nullCheck(x10aux::nullCheck(index)->
                                          FMGL(array))->x10::array::Array<x10_int>::apply(
                        i,
                        j);
                    
                    //#line 427 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int rowCount =
                      ((x10aux::ref<DoubleMatrix>)this)->
                        FMGL(n);
                    
                    //#line 428 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int columnCount =
                      ((x10aux::ref<DoubleMatrix>)this)->
                        FMGL(m);
                    
                    //#line 429 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
                    if ((((li) > (((x10_int) ((rowCount) * (columnCount)))))) ||
                        (((li) < (((x10_int)1)))))
                    {
                        
                        //#line 430 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                        DoubleMatrix::error(
                          x10aux::string_utils::lit("At linear indexing assignment: Out of bounds linear index."));
                    }
                    
                    //#line 431 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int ii =
                      ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) % (rowCount)));
                    
                    //#line 432 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                    x10_int jj =
                      ((x10_int) ((((x10_int) ((li) - (((x10_int)1))))) / (rowCount)));
                    
                    //#line 433 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                    x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                        FMGL(array))->x10::array::Array<x10_double>::set(
                      x10aux::nullCheck(x10aux::nullCheck(v)->
                                          FMGL(array))->x10::array::Array<x10_double>::apply(
                        i,
                        j),
                      ii,
                      jj);
                }
            }
            
        }
    }
    
}

//#line 438 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10_double v, x10aux::ref<BooleanMatrix> index) {
    
    //#line 440 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                   FMGL(n),
                                 x10aux::nullCheck(index)->
                                   FMGL(n)))) ||
        ((!x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                   FMGL(m),
                                 x10aux::nullCheck(index)->
                                   FMGL(m)))))
    {
        
        //#line 441 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At logical indexing: Mismatch of array and index dimensions."));
    }
    
    //#line 443 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 443 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (((x10aux::ref<DoubleMatrix>)this)->
                                         FMGL(n)));
             
             //#line 443 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 444 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int j;
                for (
                     //#line 444 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     j =
                       ((x10_int)0);
                     ((j) < (((x10aux::ref<DoubleMatrix>)this)->
                               FMGL(m)));
                     
                     //#line 444 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     j =
                       ((x10_int) ((j) + (((x10_int)1)))))
                {
                    
                    //#line 445 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
                    if (x10aux::nullCheck(x10aux::nullCheck(index)->
                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                          i,
                          j))
                    {
                        
                        //#line 449 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                        x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                            FMGL(array))->x10::array::Array<x10_double>::set(
                          v,
                          i,
                          j);
                    }
                    
                }
            }
            
        }
    }
    
}

//#line 453 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::set(x10aux::ref<DoubleMatrix> v,
                       x10aux::ref<BooleanMatrix> index) {
    
    //#line 454 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if (((!x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                   FMGL(n),
                                 x10aux::nullCheck(index)->
                                   FMGL(n)))) ||
        ((!x10aux::struct_equals(((x10aux::ref<DoubleMatrix>)this)->
                                   FMGL(m),
                                 x10aux::nullCheck(index)->
                                   FMGL(m)))))
    {
        
        //#line 455 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At logical indexing: Mismatch of array and index dimensions."));
    }
    
    //#line 456 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(x10aux::nullCheck(v)->
                                  FMGL(n),
                                ((x10_int)1))))
    {
        
        //#line 457 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At logical indexing: Right hand side should be a horizontal matrix."));
    }
    
    //#line 458 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10_int k = ((x10_int)0);
    
    //#line 459 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int j;
        for (
             //#line 459 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             j = ((x10_int)0); ((j) < (((x10aux::ref<DoubleMatrix>)this)->
                                         FMGL(m)));
             
             //#line 459 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             j =
               ((x10_int) ((j) + (((x10_int)1)))))
        {
            
            //#line 460 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int i;
                for (
                     //#line 460 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     i =
                       ((x10_int)0);
                     ((i) < (((x10aux::ref<DoubleMatrix>)this)->
                               FMGL(n)));
                     
                     //#line 460 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     i =
                       ((x10_int) ((i) + (((x10_int)1)))))
                {
                    
                    //#line 461 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
                    if (x10aux::nullCheck(x10aux::nullCheck(index)->
                                            FMGL(array))->x10::array::Array<x10_boolean>::apply(
                          i,
                          j))
                    {
                        
                        //#line 462 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                        x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                            FMGL(array))->x10::array::Array<x10_double>::set(
                          x10aux::nullCheck(x10aux::nullCheck(v)->
                                              FMGL(array))->x10::array::Array<x10_double>::apply(
                            ((x10_int)0),
                            k),
                          i,
                          j);
                        
                        //#line 463 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                        k =
                          ((x10_int) ((k) + (((x10_int)1))));
                    }
                    
                }
            }
            
        }
    }
    
    //#line 465 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((!x10aux::struct_equals(k, x10aux::nullCheck(index)->
                                     FMGL(m))))
    {
        
        //#line 466 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
        DoubleMatrix::error(
          x10aux::string_utils::lit("At logical indexing: Mismatch between number of true values in the index and the number of columns of the right hand side."));
    }
    
}

//#line 470 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<x10::lang::String> DoubleMatrix::toString(
  ) {
    
    //#line 471 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::String> s = x10aux::string_utils::lit("");
    
    //#line 472 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
    {
        x10_int i;
        for (
             //#line 472 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
             i = ((x10_int)0); ((i) < (((x10aux::ref<DoubleMatrix>)this)->
                                         FMGL(n)));
             
             //#line 472 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
             i =
               ((x10_int) ((i) + (((x10_int)1)))))
        {
            
            //#line 473 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.For_c
            {
                x10_int j;
                for (
                     //#line 473 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
                     j =
                       ((x10_int)0);
                     ((j) < (((x10aux::ref<DoubleMatrix>)this)->
                               FMGL(m)));
                     
                     //#line 473 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                     j =
                       ((x10_int) ((j) + (((x10_int)1)))))
                {
                    
                    //#line 474 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                    s =
                      ((s) + (x10aux::to_string(x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                                                    FMGL(array))->x10::array::Array<x10_double>::apply(
                                                  i,
                                                  j))));
                    
                    //#line 475 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                    s =
                      ((s) + (x10aux::string_utils::lit("\t")));
                }
            }
            
            //#line 477 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
            if ((!x10aux::struct_equals(i,
                                        ((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                                                       FMGL(n)) - (((x10_int)1)))))))
            {
                
                //#line 478 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
                s =
                  ((s) + (x10aux::string_utils::lit("\n")));
            }
            
        }
    }
    
    //#line 480 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return s;
    
}

//#line 496 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_int DoubleMatrix::dim(x10_int i) {
    
    //#line 497 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(i, ((x10_int)1))))
    {
        
        //#line 498 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return ((x10aux::ref<DoubleMatrix>)this)->
                 FMGL(n);
        
    }
    else
    
    //#line 499 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(i,
                               ((x10_int)2))))
    {
        
        //#line 500 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return ((x10aux::ref<DoubleMatrix>)this)->
                 FMGL(m);
        
    }
    else
    {
        
        //#line 502 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
        return ((x10_int) ((((x10_int) ((x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                                             FMGL(array))->
                                           FMGL(region)->max(
                                           ((x10_int) ((i) - (((x10_int)1)))))) - (x10aux::nullCheck(((x10aux::ref<DoubleMatrix>)this)->
                                                                                                       FMGL(array))->
                                                                                     FMGL(region)->min(
                                                                                     ((x10_int) ((i) - (((x10_int)1))))))))) + (((x10_int)1))));
        
    }
    
}

//#line 506 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<x10::lang::Iterable<x10_double> >
  DoubleMatrix::values(
  ) {
    
    //#line 507 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Iterable<x10_double> > >(DoubleMatrix__ValueIterable::_make(((x10aux::ref<DoubleMatrix>)this)));
    
}

//#line 533 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10aux::ref<DoubleMatrix> DoubleMatrix::transpose(
  ) {
    
    //#line 535 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Region> r = x10aux::nullCheck((x10::array::Region::makeRectangular(
                                                             ((x10_int)0),
                                                             ((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                                                                            FMGL(m)) - (((x10_int)1)))))))->__times(
                                          x10::array::Region::makeRectangular(
                                            ((x10_int)0),
                                            ((x10_int) ((((x10aux::ref<DoubleMatrix>)this)->
                                                           FMGL(n)) - (((x10_int)1))))));
    
    //#line 536 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::array::Array<x10_double> > a =
      x10::array::Array<x10_double>::_make(r,
                                           x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> > >(x10aux::ref<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(x10aux::ref<DoubleMatrix__closure__14>(new (x10aux::alloc<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >(sizeof(DoubleMatrix__closure__14)))DoubleMatrix__closure__14(this)))));
    
    //#line 544 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return DoubleMatrix::_make(a, ((x10aux::ref<DoubleMatrix>)this)->
                                    FMGL(m),
                               ((x10aux::ref<DoubleMatrix>)this)->
                                 FMGL(n));
    
}

//#line 548 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::error(x10aux::ref<x10::lang::String> s) {
    
    //#line 549 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    x10aux::nullCheck(x10::io::Console::FMGL(OUT__get)())->x10::io::Printer::println(
      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(s));
    
    //#line 551 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Throw_c
    x10aux::throwException(x10aux::nullCheck(x10::lang::RuntimeException::_make()));
}

//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_int DoubleMatrix::n() {
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return ((x10aux::ref<DoubleMatrix>)this)->
             FMGL(n);
    
}

//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
x10_int DoubleMatrix::m() {
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10Return_c
    return ((x10aux::ref<DoubleMatrix>)this)->
             FMGL(m);
    
}

//#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": x10.ast.X10MethodDecl_c
void DoubleMatrix::__fieldInitializers110(
  ) {
    
    //#line 6 "/media/MOHSENHD/Prog/Library/LesaniLib/src/lesani/x10src/shared/src/DoubleMatrix.x10": polyglot.ast.Eval_c
    ((x10aux::ref<DoubleMatrix>)this)->FMGL(array) =
      x10aux::class_cast_unchecked<x10aux::ref<x10::array::Array<x10_double> > >(x10aux::null);
}
const x10aux::serialization_id_t DoubleMatrix::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix::_deserializer<x10::lang::Reference>);

void DoubleMatrix::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(array));
    buf.write(this->FMGL(n));
    buf.write(this->FMGL(m));
    
}

void DoubleMatrix::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(array) = buf.read<x10aux::ref<x10::array::Array<x10_double> > >();
    FMGL(n) = buf.read<x10_int>();
    FMGL(m) = buf.read<x10_int>();
}

x10aux::RuntimeType DoubleMatrix::rtt;
void DoubleMatrix::_initRTT() {
    if (rtt.initStageOne(&rtt)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    rtt.initStageTwo("DoubleMatrix", 1, parents, 0, NULL, NULL);
}
x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__0>DoubleMatrix__closure__0::_itable(&DoubleMatrix__closure__0::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__0::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__0::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__0::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__0::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__0::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__1>DoubleMatrix__closure__1::_itable(&DoubleMatrix__closure__1::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__1::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__1::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__1::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__1::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__1::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__2>DoubleMatrix__closure__2::_itable(&DoubleMatrix__closure__2::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__2::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__2::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__2::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__2::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__2::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__3>DoubleMatrix__closure__3::_itable(&DoubleMatrix__closure__3::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__3::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__3::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__3::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__3::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__3::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__4>DoubleMatrix__closure__4::_itable(&DoubleMatrix__closure__4::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__4::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__4::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__4::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__4::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__4::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__5>DoubleMatrix__closure__5::_itable(&DoubleMatrix__closure__5::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__5::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__5::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__5::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__5::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__5::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__6>DoubleMatrix__closure__6::_itable(&DoubleMatrix__closure__6::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__6::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__6::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__6::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__6::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__6::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__7>DoubleMatrix__closure__7::_itable(&DoubleMatrix__closure__7::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__7::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__7::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__7::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__7::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__7::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__8>DoubleMatrix__closure__8::_itable(&DoubleMatrix__closure__8::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__8::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__8::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__8::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__8::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__8::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__9>DoubleMatrix__closure__9::_itable(&DoubleMatrix__closure__9::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__9::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__9::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__9::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__9::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__9::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__10>DoubleMatrix__closure__10::_itable(&DoubleMatrix__closure__10::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__10::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__10::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__10::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__10::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__10::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__11>DoubleMatrix__closure__11::_itable(&DoubleMatrix__closure__11::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__11::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__11::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__11::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__11::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__11::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__12>DoubleMatrix__closure__12::_itable(&DoubleMatrix__closure__12::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__12::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__12::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__12::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__12::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__12::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__13>DoubleMatrix__closure__13::_itable(&DoubleMatrix__closure__13::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__13::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__13::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__13::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__13::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__13::_deserialize<x10::lang::Reference>);

x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double>::itable<DoubleMatrix__closure__14>DoubleMatrix__closure__14::_itable(&DoubleMatrix__closure__14::apply, &x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &DoubleMatrix__closure__14::toString, &x10::lang::Closure::typeName);x10aux::itable_entry DoubleMatrix__closure__14::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_1<x10aux::ref<x10::array::Point>, x10_double> >, &DoubleMatrix__closure__14::_itable),x10aux::itable_entry(NULL, NULL)};

const x10aux::serialization_id_t DoubleMatrix__closure__14::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(DoubleMatrix__closure__14::_deserialize<x10::lang::Reference>);

