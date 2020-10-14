#ifndef __BOOLEANMATRIX_H
#define __BOOLEANMATRIX_H

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
class Boolean;
} } 
#include <x10/lang/Boolean.struct_h>
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
class BooleanMatrix__ValueIterable;
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace io { 
class Console;
} } 
namespace x10 { namespace lang { 
class RuntimeException;
} } 
class BooleanMatrix : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    x10_int FMGL(n);
    
    x10_int FMGL(m);
    
    void _instance_init();
    
    x10aux::ref<x10::array::Array<x10_boolean> > FMGL(array);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_boolean> > array);
    
    static x10aux::ref<BooleanMatrix> _make(x10aux::ref<x10::array::Array<x10_boolean> > array);
    
    void _constructor(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_boolean> > > > arrays);
    
    static x10aux::ref<BooleanMatrix> _make(x10aux::ref<x10::array::Array<x10aux::ref<x10::array::Array<x10_boolean> > > > arrays);
    
    void _constructor(x10aux::ref<x10::array::Array<x10_boolean> > array,
                      x10_int nv,
                      x10_int mv);
    
    static x10aux::ref<BooleanMatrix> _make(x10aux::ref<x10::array::Array<x10_boolean> > array,
                                            x10_int nv,
                                            x10_int mv);
    
    virtual x10_boolean apply(x10_int i, x10_int j);
    virtual x10aux::ref<BooleanMatrix> apply(x10aux::ref<IntMatrix> i,
                                             x10aux::ref<IntMatrix> j);
    virtual x10aux::ref<BooleanMatrix> apply(x10_int i, x10aux::ref<IntMatrix> j);
    virtual x10aux::ref<BooleanMatrix> apply(x10aux::ref<IntMatrix> i,
                                             x10_int j);
    virtual x10aux::ref<BooleanMatrix> apply(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyHH(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyHV(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyVH(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyVV(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyHM(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyVM(x10aux::ref<IntMatrix> index);
    virtual x10aux::ref<BooleanMatrix> applyMM(x10aux::ref<IntMatrix> index);
    virtual x10_boolean apply(x10_int li);
    virtual x10aux::ref<BooleanMatrix> apply(x10aux::ref<BooleanMatrix> index);
    virtual x10aux::ref<BooleanMatrix> singleColumn();
    virtual void set(x10_boolean v, x10_int i, x10_int j);
    virtual void set(x10_boolean v, x10aux::ref<IntMatrix> index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10aux::ref<BooleanMatrix> v, x10aux::ref<IntMatrix> index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10_boolean v, x10_int index1, x10aux::ref<IntMatrix> index2);
    virtual void set(x10aux::ref<BooleanMatrix> v, x10_int index1,
                     x10aux::ref<IntMatrix> index2);
    virtual void set(x10_boolean v, x10aux::ref<IntMatrix> index1,
                     x10_int index2);
    virtual void set(x10aux::ref<BooleanMatrix> v, x10aux::ref<IntMatrix> index1,
                     x10_int index2);
    virtual void set(x10_boolean v, x10aux::ref<IntMatrix> index);
    virtual void set(x10aux::ref<BooleanMatrix> v,
                     x10aux::ref<IntMatrix> index);
    virtual void set(x10_boolean v, x10aux::ref<BooleanMatrix> index);
    virtual void set(x10aux::ref<BooleanMatrix> v,
                     x10aux::ref<BooleanMatrix> index);
    virtual x10aux::ref<x10::lang::String> toString(
      );
    virtual x10_int dim(x10_int i);
    virtual x10aux::ref<x10::lang::Iterable<x10_boolean> >
      values(
      );
    virtual x10aux::ref<BooleanMatrix> transpose(
      );
    static void error(x10aux::ref<x10::lang::String> s);
    x10_int n();
    x10_int m();
    void __fieldInitializers90();
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // BOOLEANMATRIX_H

class BooleanMatrix;

#ifndef BOOLEANMATRIX_H_NODEPS
#define BOOLEANMATRIX_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/Int.h>
#include <x10/array/Array.h>
#include <x10/lang/Boolean.h>
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
#include <x10/util/ArrayList.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/String.h>
#include <x10/lang/Iterable.h>
#include <BooleanMatrix__ValueIterable.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/io/Console.h>
#include <x10/lang/RuntimeException.h>
#ifndef BOOLEANMATRIX_H_GENERICS
#define BOOLEANMATRIX_H_GENERICS
template<class __T> x10aux::ref<__T> BooleanMatrix::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<BooleanMatrix> this_ = new (memset(x10aux::alloc<BooleanMatrix>(), 0, sizeof(BooleanMatrix))) BooleanMatrix();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // BOOLEANMATRIX_H_GENERICS
#endif // __BOOLEANMATRIX_H_NODEPS
