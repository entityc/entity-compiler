# Server Endpoints

The interfaces extension to the Entity Language is used to describe server endpoints and can be used to generate both server code and client side code to those endpoints.

## Abstract Operations

It is best to define a single interface with abstract operations that you then extend to create the endpoints you need.

A majority of endpoints are based on a specific entity so it's best to define abstract operations that support an entity context. As well, it's also best to support a domain context, this allows it to automatically take on a variable naming method.

A common set of abstraction operations would be:

|Operation|Description|
|-----|-----|
|Create|This would allow an object of the entity to be created.|
|Get List|This would get all objects of an entity. In practice this should support some type of paging if the number of objects could  be large.|
|Get By ID|This would get an object of an entity by some unique identifier.|
|Update|This would update an object of an entity.|
|Delete by ID|This would delete a specific object of an entity by its unique identifier.|

### Request Parameters

The parameters defined in the abstract operations should be those you want to support by all operations that extend them. This can be for instance parameters used for paging since you may want all your Get List operations to have paging capability.

Extended operations can always support additional parameters. The templates will need to 

### Response Status

The status returned by the abstract operations should be the usual that those type of endpoints return. The extended operations can specify additional very specific status.

The templates ultimately need to support the defined response status so abstract operations should only define returned status that your templates can support.

