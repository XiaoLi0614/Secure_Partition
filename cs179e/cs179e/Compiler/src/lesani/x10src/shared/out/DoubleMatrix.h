#ifndef __DOUBLEMATRIX_H
#define __DOUBLEMATRIX_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_INT_STRUCT_H_NODEPS
#include <x10/lang/Int.struct_h>
#undef X10_LANG_INT_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
class Double;
} } 
#include <x10/lang/Double.struct_h>
namespace x10 { namespace array { 
class Region;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace array { 
class Point;
} } 
namespace x10 { namespace array { 
template<class FMGL(T)> class Array;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
class IntMatrix;
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
class BooleanMatrix;
namespace x10 { namespace util { 
template<class FMGL(T)> class ArrayList;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class Iterable;
} } 
class DoubleMatrix__ValueIterable;
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace io { 
class Console;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
} } 
class DoubleMatrix : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(n);
    
    x10_int FMGL(m);
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<x10_double> > FMGL(array);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_double> > array);
    
    static x10aux::ref<DoubleMatrix> _make(x10aux::ref<x10::array::Array<x10_double> > array);
    
    void _constructor(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > arrays);
    
    static x10aux::ref<DoubleMatrix> _make(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_double> > > > arrays);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_double> > array, x10_int nv,
                      x10_int mv);
    
    static x10aux::ref<DoubleMatrix> _make(x10aux::ref<x10::array::Array<x10_double> > array,
                                           x10_int nv,
                                           x10_int mv);
    
    virtual x10_double apply(x10_int i, x10_int j);
    virtual x10aux::ref<DoubleMatrix> apply(x10aux::ref<IntMatrix> i,
                                            x10aux::ref<IntMatrix> j);
    virtual x10aux::ref<DoubleMatrix> apply(x10_int i, x10aux::ref<IntMatrix> j);
    virtual x10aux::ref<DoubleMatrix> apply(x10aux::ref<IntMatrix> i,
                                            x10_int j);
    virtual x10aux::ref<DoubleMatrix> apply(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyHH(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyHV(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyVH(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyVV(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyHM(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyVM(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<DoubleMatrix> applyMM(x10aux::ref<IntMatrix> index);
    virtual x10_double apply(x10_int li);
    virtual x10aux::ref<DoubleMatrix> apply(x10aux::ref<BooleanMatrix> index);
    virtual x10aux::ref<DoubleMatrix> singleColumn();
    virtual void set(x10_double v, x10_int i, x10_int j);
    virtual void set(x10_double v, x10aux::ref<IntMatrix> index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10aux::ref<DoubleMatrix> v, x10aux::ref<IntMatrix> index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10_double v, x10_int index1, x10aux::ref<IntMatrix> index2);
    virtual void set(x10aux::ref<DoubleMatrix> v, x10_int index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10_double v, x10aux::ref<IntMatrix> index1,
                     x10_int index2);
    virtual void set(x10aux::ref<DoubleMatrix> v, x10aux::ref<IntMatrix> index1,
                     x10_int index2);
    virtual void set(x10_double v, x10aux::ref<IntMatrix> index);
    virtual void set(x10aux::ref<DoubleMatrix> v, x10aux::ref<IntMatrix> index);
    virtual void set(x10_double v, x10aux::ref<BooleanMatrix> index);
    virtual void set(x10aux::ref<DoubleMatrix> v, x10aux::ref<BooleanMatrix> index);
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10_int dim(x10_int i);
    virtual x10aux::ref<x10::lang::Iterable<x10_double> >
      values(
      );
    virtual x10aux::ref<DoubleMatrix> transpose();
    static void error(x10aux::ref<x10::lang::String> s);
    x10_int n();
    x10_int m();
    void __fieldInitializers110();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // DOUBLEMATRIX_H

class DoubleMatrix;

#ifndef DOUBLEMATRIX_H_NODEPS
#define DOUBLEMATRIX_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Int.h>
#include <x10/array/Array.h>
#include <x10/lang/Double.h>
#include <x10/array/Region.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Point.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <IntMatrix.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <BooleanMatrix.h>
#include <x10/util/ArrayList.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/String.h>
#include <x10/lang/Iterable.h>
#include <DoubleMatrix__ValueIterable.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/io/Console.h>
#include <x10/lang/RuntimeException.h>
#ifndef DOUBLEMATRIX_H_GENERICS
#define DOUBLEMATRIX_H_GENERICS
template<class __T> x10aux::ref<__T> DoubleMatrix::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<DoubleMatrix> this_ = new (memset(x10aux::alloc<DoubleMatrix>(), 0, sizeof(DoubleMatrix))) DoubleMatrix();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // DOUBLEMATRIX_H_GENERICS
#endif // __DOUBLEMATRIX_H_NODEPS
