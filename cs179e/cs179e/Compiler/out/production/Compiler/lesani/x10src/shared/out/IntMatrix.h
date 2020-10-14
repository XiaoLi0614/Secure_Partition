#ifndef __INTMATRIX_H
#define __INTMATRIX_H

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
class IntMatrix__ValueIterable;
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace io { 
class Console;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
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
class IntMatrix : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(n);
    
    x10_int FMGL(m);
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<x10_int> > FMGL(array);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_int> > array);
    
    static x10aux::ref<IntMatrix> _make(x10aux::ref<x10::array::Array<x10_int> > array);
    
    void _constructor(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_int> > > > arrays);
    
    static x10aux::ref<IntMatrix> _make(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_int> > > > arrays);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_int> > array, x10_int nv,
                      x10_int mv);
    
    static x10aux::ref<IntMatrix> _make(x10aux::ref<x10::array::Array<x10_int> > array,
                                        x10_int nv,
                                        x10_int mv);
    
    virtual x10_int apply(x10_int i, x10_int j);
    virtual x10aux::ref<IntMatrix> apply(x10aux::ref<IntMatrix> i,
                                         x10aux::ref<IntMatrix> j);
    virtual x10aux::ref<IntMatrix> apply(x10_int i, x10aux::ref<IntMatrix> j);
    virtual x10aux::ref<IntMatrix> apply(x10aux::ref<IntMatrix> i,
                                         x10_int j);
    virtual x10aux::ref<IntMatrix> apply(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyHH(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyHV(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyVH(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyVV(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyHM(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyVM(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<IntMatrix> applyMM(x10aux::ref<IntMatrix> index);
    virtual x10_int apply(x10_int li);
    virtual x10aux::ref<IntMatrix> apply(x10aux::ref<BooleanMatrix> index);
    virtual x10aux::ref<IntMatrix> singleColumn();
    virtual void set(x10_int v, x10_int i, x10_int j);
    virtual void set(x10_int v, x10aux::ref<IntMatrix> index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10aux::ref<IntMatrix> v, x10aux::ref<IntMatrix> index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10_int v, x10_int index1, x10aux::ref<IntMatrix> index2);
    virtual void set(x10aux::ref<IntMatrix> v, x10_int index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10_int v, x10aux::ref<IntMatrix> index1,
                     x10_int index2);
    virtual void set(x10aux::ref<IntMatrix> v, x10aux::ref<IntMatrix> index1,
                     x10_int index2);
    virtual void set(x10_int v, x10aux::ref<IntMatrix> index);
    virtual void set(x10aux::ref<IntMatrix> v, x10aux::ref<IntMatrix> index);
    virtual void set(x10_int v, x10aux::ref<BooleanMatrix> index);
    virtual void set(x10aux::ref<IntMatrix> v, x10aux::ref<BooleanMatrix> index);
    virtual x10aux::ref<x10::lang::String> toString();
    virtual x10_int dim(x10_int i);
    virtual x10aux::ref<x10::lang::Iterable<x10_int> > values(
      );
    virtual x10aux::ref<IntMatrix> transpose();
    static void error(x10aux::ref<x10::lang::String> s);
    void _constructor(x10_int i1, x10_int i2);
    
    static x10aux::ref<IntMatrix> _make(x10_int i1, x10_int i2);
    
    static x10aux::ref<IntMatrix> __percent(x10aux::ref<IntMatrix> x,
                                            x10aux::ref<IntMatrix> y);
    static x10aux::ref<IntMatrix> __percent(x10_int x, x10aux::ref<IntMatrix> y);
    static x10aux::ref<IntMatrix> __percent(x10aux::ref<IntMatrix> x,
                                            x10_int y);
    x10_int n();
    x10_int m();
    void __fieldInitializers118();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // INTMATRIX_H

class IntMatrix;

#ifndef INTMATRIX_H_NODEPS
#define INTMATRIX_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Int.h>
#include <x10/array/Array.h>
#include <x10/array/Region.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/array/Point.h>
#include <x10/array/Array.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
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
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <BooleanMatrix.h>
#include <x10/util/ArrayList.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/String.h>
#include <x10/lang/Iterable.h>
#include <IntMatrix__ValueIterable.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/io/Console.h>
#include <x10/lang/RuntimeException.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#ifndef INTMATRIX_H_GENERICS
#define INTMATRIX_H_GENERICS
template<class __T> x10aux::ref<__T> IntMatrix::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<IntMatrix> this_ = new (memset(x10aux::alloc<IntMatrix>(), 0, sizeof(IntMatrix))) IntMatrix();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // INTMATRIX_H_GENERICS
#endif // __INTMATRIX_H_NODEPS
