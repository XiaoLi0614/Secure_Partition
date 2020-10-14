#ifndef __MAPREDUCELAUNCHER__MYMAPREDUCER_H
#define __MAPREDUCELAUNCHER__MYMAPREDUCER_H

#include <x10rt17.h>

#define MAPREDUCER_H_NODEPS
#include <MapReducer.h>
#undef MAPREDUCER_H_NODEPS
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
namespace x10 { namespace util { 
template<class FMGL(T), class FMGL(U)> class Pair;
} } 
#include <x10/util/Pair.struct_h>
template<class FMGL(T)> class MapReduceLauncher__HashSet;
namespace x10 { namespace util { 
template<class FMGL(T)> class Set;
} } 
class MapReduceLauncher__MyMapReducer : public MapReducer<x10aux::ref<x10::lang::String>, x10aux::ref<x10::lang::String>, x10_int, x10_int>
  {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    virtual x10aux::ref<x10::util::Set<x10::util::Pair<x10aux::ref<x10::lang::String>, x10_int> > >
      map(
      x10aux::ref<x10::lang::String> line);
    virtual x10_int reduce(x10aux::ref<x10::util::Set<x10_int> > interms);
    void _constructor();
    
    
    // Serialization
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf, x10aux::addr_map& m);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
#endif // MAPREDUCELAUNCHER__MYMAPREDUCER_H

class MapReduceLauncher__MyMapReducer;

#ifndef MAPREDUCELAUNCHER__MYMAPREDUCER_H_NODEPS
#define MAPREDUCELAUNCHER__MYMAPREDUCER_H_NODEPS
#include <MapReducer.h>
#include <x10/lang/String.h>
#include <x10/lang/Int.h>
#include <x10/util/Set.h>
#include <x10/util/Pair.h>
#include <MapReduceLauncher__HashSet.h>
#include <x10/util/Set.h>
#ifndef MAPREDUCELAUNCHER__MYMAPREDUCER_H_GENERICS
#define MAPREDUCELAUNCHER__MYMAPREDUCER_H_GENERICS
#endif // MAPREDUCELAUNCHER__MYMAPREDUCER_H_GENERICS
#endif // __MAPREDUCELAUNCHER__MYMAPREDUCER_H_NODEPS
