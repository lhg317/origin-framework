/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.resource.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-07")
public class RpcResource implements org.apache.thrift.TBase<RpcResource, RpcResource._Fields>, java.io.Serializable, Cloneable, Comparable<RpcResource> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcResource");

  private static final org.apache.thrift.protocol.TField RESOURCE_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceID", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField RESOURCE_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField RESOURCE_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceCode", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField RESOURCE_CATEGORY_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceCategory", org.apache.thrift.protocol.TType.STRUCT, (short)5);
  private static final org.apache.thrift.protocol.TField RESOURCE_OPERATE_LIST_FIELD_DESC = new org.apache.thrift.protocol.TField("resourceOperateList", org.apache.thrift.protocol.TType.LIST, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcResourceStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcResourceTupleSchemeFactory();

  private int resourceID; // required
  private java.lang.String resourceName; // required
  private java.lang.String resourceCode; // required
  private java.lang.String description; // required
  private RpcResourceCategory resourceCategory; // required
  private java.util.List<RpcResourceOperate> resourceOperateList; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    RESOURCE_ID((short)1, "resourceID"),
    RESOURCE_NAME((short)2, "resourceName"),
    RESOURCE_CODE((short)3, "resourceCode"),
    DESCRIPTION((short)4, "description"),
    RESOURCE_CATEGORY((short)5, "resourceCategory"),
    RESOURCE_OPERATE_LIST((short)6, "resourceOperateList");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // RESOURCE_ID
          return RESOURCE_ID;
        case 2: // RESOURCE_NAME
          return RESOURCE_NAME;
        case 3: // RESOURCE_CODE
          return RESOURCE_CODE;
        case 4: // DESCRIPTION
          return DESCRIPTION;
        case 5: // RESOURCE_CATEGORY
          return RESOURCE_CATEGORY;
        case 6: // RESOURCE_OPERATE_LIST
          return RESOURCE_OPERATE_LIST;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __RESOURCEID_ISSET_ID = 0;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.RESOURCE_ID, new org.apache.thrift.meta_data.FieldMetaData("resourceID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.RESOURCE_NAME, new org.apache.thrift.meta_data.FieldMetaData("resourceName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RESOURCE_CODE, new org.apache.thrift.meta_data.FieldMetaData("resourceCode", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.RESOURCE_CATEGORY, new org.apache.thrift.meta_data.FieldMetaData("resourceCategory", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, RpcResourceCategory.class)));
    tmpMap.put(_Fields.RESOURCE_OPERATE_LIST, new org.apache.thrift.meta_data.FieldMetaData("resourceOperateList", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRUCT            , "RpcResourceOperate"))));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcResource.class, metaDataMap);
  }

  public RpcResource() {
  }

  public RpcResource(
    int resourceID,
    java.lang.String resourceName,
    java.lang.String resourceCode,
    java.lang.String description,
    RpcResourceCategory resourceCategory,
    java.util.List<RpcResourceOperate> resourceOperateList)
  {
    this();
    this.resourceID = resourceID;
    setResourceIDIsSet(true);
    this.resourceName = resourceName;
    this.resourceCode = resourceCode;
    this.description = description;
    this.resourceCategory = resourceCategory;
    this.resourceOperateList = resourceOperateList;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcResource(RpcResource other) {
    __isset_bitfield = other.__isset_bitfield;
    this.resourceID = other.resourceID;
    if (other.isSetResourceName()) {
      this.resourceName = other.resourceName;
    }
    if (other.isSetResourceCode()) {
      this.resourceCode = other.resourceCode;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
    if (other.isSetResourceCategory()) {
      this.resourceCategory = new RpcResourceCategory(other.resourceCategory);
    }
    if (other.isSetResourceOperateList()) {
      java.util.List<RpcResourceOperate> __this__resourceOperateList = new java.util.ArrayList<RpcResourceOperate>(other.resourceOperateList.size());
      for (RpcResourceOperate other_element : other.resourceOperateList) {
        __this__resourceOperateList.add(other_element);
      }
      this.resourceOperateList = __this__resourceOperateList;
    }
  }

  public RpcResource deepCopy() {
    return new RpcResource(this);
  }

  @Override
  public void clear() {
    setResourceIDIsSet(false);
    this.resourceID = 0;
    this.resourceName = null;
    this.resourceCode = null;
    this.description = null;
    this.resourceCategory = null;
    this.resourceOperateList = null;
  }

  public int getResourceID() {
    return this.resourceID;
  }

  public void setResourceID(int resourceID) {
    this.resourceID = resourceID;
    setResourceIDIsSet(true);
  }

  public void unsetResourceID() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __RESOURCEID_ISSET_ID);
  }

  /** Returns true if field resourceID is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceID() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __RESOURCEID_ISSET_ID);
  }

  public void setResourceIDIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __RESOURCEID_ISSET_ID, value);
  }

  public java.lang.String getResourceName() {
    return this.resourceName;
  }

  public void setResourceName(java.lang.String resourceName) {
    this.resourceName = resourceName;
  }

  public void unsetResourceName() {
    this.resourceName = null;
  }

  /** Returns true if field resourceName is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceName() {
    return this.resourceName != null;
  }

  public void setResourceNameIsSet(boolean value) {
    if (!value) {
      this.resourceName = null;
    }
  }

  public java.lang.String getResourceCode() {
    return this.resourceCode;
  }

  public void setResourceCode(java.lang.String resourceCode) {
    this.resourceCode = resourceCode;
  }

  public void unsetResourceCode() {
    this.resourceCode = null;
  }

  /** Returns true if field resourceCode is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceCode() {
    return this.resourceCode != null;
  }

  public void setResourceCodeIsSet(boolean value) {
    if (!value) {
      this.resourceCode = null;
    }
  }

  public java.lang.String getDescription() {
    return this.description;
  }

  public void setDescription(java.lang.String description) {
    this.description = description;
  }

  public void unsetDescription() {
    this.description = null;
  }

  /** Returns true if field description is set (has been assigned a value) and false otherwise */
  public boolean isSetDescription() {
    return this.description != null;
  }

  public void setDescriptionIsSet(boolean value) {
    if (!value) {
      this.description = null;
    }
  }

  public RpcResourceCategory getResourceCategory() {
    return this.resourceCategory;
  }

  public void setResourceCategory(RpcResourceCategory resourceCategory) {
    this.resourceCategory = resourceCategory;
  }

  public void unsetResourceCategory() {
    this.resourceCategory = null;
  }

  /** Returns true if field resourceCategory is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceCategory() {
    return this.resourceCategory != null;
  }

  public void setResourceCategoryIsSet(boolean value) {
    if (!value) {
      this.resourceCategory = null;
    }
  }

  public int getResourceOperateListSize() {
    return (this.resourceOperateList == null) ? 0 : this.resourceOperateList.size();
  }

  public java.util.Iterator<RpcResourceOperate> getResourceOperateListIterator() {
    return (this.resourceOperateList == null) ? null : this.resourceOperateList.iterator();
  }

  public void addToResourceOperateList(RpcResourceOperate elem) {
    if (this.resourceOperateList == null) {
      this.resourceOperateList = new java.util.ArrayList<RpcResourceOperate>();
    }
    this.resourceOperateList.add(elem);
  }

  public java.util.List<RpcResourceOperate> getResourceOperateList() {
    return this.resourceOperateList;
  }

  public void setResourceOperateList(java.util.List<RpcResourceOperate> resourceOperateList) {
    this.resourceOperateList = resourceOperateList;
  }

  public void unsetResourceOperateList() {
    this.resourceOperateList = null;
  }

  /** Returns true if field resourceOperateList is set (has been assigned a value) and false otherwise */
  public boolean isSetResourceOperateList() {
    return this.resourceOperateList != null;
  }

  public void setResourceOperateListIsSet(boolean value) {
    if (!value) {
      this.resourceOperateList = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case RESOURCE_ID:
      if (value == null) {
        unsetResourceID();
      } else {
        setResourceID((java.lang.Integer)value);
      }
      break;

    case RESOURCE_NAME:
      if (value == null) {
        unsetResourceName();
      } else {
        setResourceName((java.lang.String)value);
      }
      break;

    case RESOURCE_CODE:
      if (value == null) {
        unsetResourceCode();
      } else {
        setResourceCode((java.lang.String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((java.lang.String)value);
      }
      break;

    case RESOURCE_CATEGORY:
      if (value == null) {
        unsetResourceCategory();
      } else {
        setResourceCategory((RpcResourceCategory)value);
      }
      break;

    case RESOURCE_OPERATE_LIST:
      if (value == null) {
        unsetResourceOperateList();
      } else {
        setResourceOperateList((java.util.List<RpcResourceOperate>)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case RESOURCE_ID:
      return getResourceID();

    case RESOURCE_NAME:
      return getResourceName();

    case RESOURCE_CODE:
      return getResourceCode();

    case DESCRIPTION:
      return getDescription();

    case RESOURCE_CATEGORY:
      return getResourceCategory();

    case RESOURCE_OPERATE_LIST:
      return getResourceOperateList();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case RESOURCE_ID:
      return isSetResourceID();
    case RESOURCE_NAME:
      return isSetResourceName();
    case RESOURCE_CODE:
      return isSetResourceCode();
    case DESCRIPTION:
      return isSetDescription();
    case RESOURCE_CATEGORY:
      return isSetResourceCategory();
    case RESOURCE_OPERATE_LIST:
      return isSetResourceOperateList();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcResource)
      return this.equals((RpcResource)that);
    return false;
  }

  public boolean equals(RpcResource that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_resourceID = true;
    boolean that_present_resourceID = true;
    if (this_present_resourceID || that_present_resourceID) {
      if (!(this_present_resourceID && that_present_resourceID))
        return false;
      if (this.resourceID != that.resourceID)
        return false;
    }

    boolean this_present_resourceName = true && this.isSetResourceName();
    boolean that_present_resourceName = true && that.isSetResourceName();
    if (this_present_resourceName || that_present_resourceName) {
      if (!(this_present_resourceName && that_present_resourceName))
        return false;
      if (!this.resourceName.equals(that.resourceName))
        return false;
    }

    boolean this_present_resourceCode = true && this.isSetResourceCode();
    boolean that_present_resourceCode = true && that.isSetResourceCode();
    if (this_present_resourceCode || that_present_resourceCode) {
      if (!(this_present_resourceCode && that_present_resourceCode))
        return false;
      if (!this.resourceCode.equals(that.resourceCode))
        return false;
    }

    boolean this_present_description = true && this.isSetDescription();
    boolean that_present_description = true && that.isSetDescription();
    if (this_present_description || that_present_description) {
      if (!(this_present_description && that_present_description))
        return false;
      if (!this.description.equals(that.description))
        return false;
    }

    boolean this_present_resourceCategory = true && this.isSetResourceCategory();
    boolean that_present_resourceCategory = true && that.isSetResourceCategory();
    if (this_present_resourceCategory || that_present_resourceCategory) {
      if (!(this_present_resourceCategory && that_present_resourceCategory))
        return false;
      if (!this.resourceCategory.equals(that.resourceCategory))
        return false;
    }

    boolean this_present_resourceOperateList = true && this.isSetResourceOperateList();
    boolean that_present_resourceOperateList = true && that.isSetResourceOperateList();
    if (this_present_resourceOperateList || that_present_resourceOperateList) {
      if (!(this_present_resourceOperateList && that_present_resourceOperateList))
        return false;
      if (!this.resourceOperateList.equals(that.resourceOperateList))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + resourceID;

    hashCode = hashCode * 8191 + ((isSetResourceName()) ? 131071 : 524287);
    if (isSetResourceName())
      hashCode = hashCode * 8191 + resourceName.hashCode();

    hashCode = hashCode * 8191 + ((isSetResourceCode()) ? 131071 : 524287);
    if (isSetResourceCode())
      hashCode = hashCode * 8191 + resourceCode.hashCode();

    hashCode = hashCode * 8191 + ((isSetDescription()) ? 131071 : 524287);
    if (isSetDescription())
      hashCode = hashCode * 8191 + description.hashCode();

    hashCode = hashCode * 8191 + ((isSetResourceCategory()) ? 131071 : 524287);
    if (isSetResourceCategory())
      hashCode = hashCode * 8191 + resourceCategory.hashCode();

    hashCode = hashCode * 8191 + ((isSetResourceOperateList()) ? 131071 : 524287);
    if (isSetResourceOperateList())
      hashCode = hashCode * 8191 + resourceOperateList.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcResource other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetResourceID()).compareTo(other.isSetResourceID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceID, other.resourceID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResourceName()).compareTo(other.isSetResourceName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceName, other.resourceName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResourceCode()).compareTo(other.isSetResourceCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceCode, other.resourceCode);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDescription()).compareTo(other.isSetDescription());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDescription()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.description, other.description);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResourceCategory()).compareTo(other.isSetResourceCategory());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceCategory()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceCategory, other.resourceCategory);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetResourceOperateList()).compareTo(other.isSetResourceOperateList());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetResourceOperateList()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.resourceOperateList, other.resourceOperateList);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcResource(");
    boolean first = true;

    sb.append("resourceID:");
    sb.append(this.resourceID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceName:");
    if (this.resourceName == null) {
      sb.append("null");
    } else {
      sb.append(this.resourceName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceCode:");
    if (this.resourceCode == null) {
      sb.append("null");
    } else {
      sb.append(this.resourceCode);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("description:");
    if (this.description == null) {
      sb.append("null");
    } else {
      sb.append(this.description);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceCategory:");
    if (this.resourceCategory == null) {
      sb.append("null");
    } else {
      sb.append(this.resourceCategory);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("resourceOperateList:");
    if (this.resourceOperateList == null) {
      sb.append("null");
    } else {
      sb.append(this.resourceOperateList);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (resourceCategory != null) {
      resourceCategory.validate();
    }
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcResourceStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcResourceStandardScheme getScheme() {
      return new RpcResourceStandardScheme();
    }
  }

  private static class RpcResourceStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcResource> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // RESOURCE_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.resourceID = iprot.readI32();
              struct.setResourceIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // RESOURCE_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.resourceName = iprot.readString();
              struct.setResourceNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // RESOURCE_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.resourceCode = iprot.readString();
              struct.setResourceCodeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // DESCRIPTION
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.description = iprot.readString();
              struct.setDescriptionIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // RESOURCE_CATEGORY
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.resourceCategory = new RpcResourceCategory();
              struct.resourceCategory.read(iprot);
              struct.setResourceCategoryIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // RESOURCE_OPERATE_LIST
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list0 = iprot.readListBegin();
                struct.resourceOperateList = new java.util.ArrayList<RpcResourceOperate>(_list0.size);
                RpcResourceOperate _elem1;
                for (int _i2 = 0; _i2 < _list0.size; ++_i2)
                {
                  _elem1 = new RpcResourceOperate();
                  _elem1.read(iprot);
                  struct.resourceOperateList.add(_elem1);
                }
                iprot.readListEnd();
              }
              struct.setResourceOperateListIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcResource struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(RESOURCE_ID_FIELD_DESC);
      oprot.writeI32(struct.resourceID);
      oprot.writeFieldEnd();
      if (struct.resourceName != null) {
        oprot.writeFieldBegin(RESOURCE_NAME_FIELD_DESC);
        oprot.writeString(struct.resourceName);
        oprot.writeFieldEnd();
      }
      if (struct.resourceCode != null) {
        oprot.writeFieldBegin(RESOURCE_CODE_FIELD_DESC);
        oprot.writeString(struct.resourceCode);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      if (struct.resourceCategory != null) {
        oprot.writeFieldBegin(RESOURCE_CATEGORY_FIELD_DESC);
        struct.resourceCategory.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.resourceOperateList != null) {
        oprot.writeFieldBegin(RESOURCE_OPERATE_LIST_FIELD_DESC);
        {
          oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, struct.resourceOperateList.size()));
          for (RpcResourceOperate _iter3 : struct.resourceOperateList)
          {
            _iter3.write(oprot);
          }
          oprot.writeListEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcResourceTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcResourceTupleScheme getScheme() {
      return new RpcResourceTupleScheme();
    }
  }

  private static class RpcResourceTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcResource> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetResourceID()) {
        optionals.set(0);
      }
      if (struct.isSetResourceName()) {
        optionals.set(1);
      }
      if (struct.isSetResourceCode()) {
        optionals.set(2);
      }
      if (struct.isSetDescription()) {
        optionals.set(3);
      }
      if (struct.isSetResourceCategory()) {
        optionals.set(4);
      }
      if (struct.isSetResourceOperateList()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetResourceID()) {
        oprot.writeI32(struct.resourceID);
      }
      if (struct.isSetResourceName()) {
        oprot.writeString(struct.resourceName);
      }
      if (struct.isSetResourceCode()) {
        oprot.writeString(struct.resourceCode);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
      if (struct.isSetResourceCategory()) {
        struct.resourceCategory.write(oprot);
      }
      if (struct.isSetResourceOperateList()) {
        {
          oprot.writeI32(struct.resourceOperateList.size());
          for (RpcResourceOperate _iter4 : struct.resourceOperateList)
          {
            _iter4.write(oprot);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcResource struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.resourceID = iprot.readI32();
        struct.setResourceIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.resourceName = iprot.readString();
        struct.setResourceNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.resourceCode = iprot.readString();
        struct.setResourceCodeIsSet(true);
      }
      if (incoming.get(3)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
      if (incoming.get(4)) {
        struct.resourceCategory = new RpcResourceCategory();
        struct.resourceCategory.read(iprot);
        struct.setResourceCategoryIsSet(true);
      }
      if (incoming.get(5)) {
        {
          org.apache.thrift.protocol.TList _list5 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.STRUCT, iprot.readI32());
          struct.resourceOperateList = new java.util.ArrayList<RpcResourceOperate>(_list5.size);
          RpcResourceOperate _elem6;
          for (int _i7 = 0; _i7 < _list5.size; ++_i7)
          {
            _elem6 = new RpcResourceOperate();
            _elem6.read(iprot);
            struct.resourceOperateList.add(_elem6);
          }
        }
        struct.setResourceOperateListIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

