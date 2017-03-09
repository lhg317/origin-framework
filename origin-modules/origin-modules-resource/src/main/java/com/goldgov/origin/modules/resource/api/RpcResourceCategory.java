/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.resource.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-03-09")
public class RpcResourceCategory implements org.apache.thrift.TBase<RpcResourceCategory, RpcResourceCategory._Fields>, java.io.Serializable, Cloneable, Comparable<RpcResourceCategory> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcResourceCategory");

  private static final org.apache.thrift.protocol.TField CATEGORY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("categoryID", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField CATEGORY_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("categoryName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField DATA_PATH_FIELD_DESC = new org.apache.thrift.protocol.TField("dataPath", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField DESCRIPTION_FIELD_DESC = new org.apache.thrift.protocol.TField("description", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField PARENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("parentID", org.apache.thrift.protocol.TType.I32, (short)5);
  private static final org.apache.thrift.protocol.TField ORDER_NUM_FIELD_DESC = new org.apache.thrift.protocol.TField("orderNum", org.apache.thrift.protocol.TType.I32, (short)6);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcResourceCategoryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcResourceCategoryTupleSchemeFactory();

  private int categoryID; // required
  private java.lang.String categoryName; // required
  private java.lang.String dataPath; // required
  private java.lang.String description; // required
  private int parentID; // required
  private int orderNum; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CATEGORY_ID((short)1, "categoryID"),
    CATEGORY_NAME((short)2, "categoryName"),
    DATA_PATH((short)3, "dataPath"),
    DESCRIPTION((short)4, "description"),
    PARENT_ID((short)5, "parentID"),
    ORDER_NUM((short)6, "orderNum");

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
        case 1: // CATEGORY_ID
          return CATEGORY_ID;
        case 2: // CATEGORY_NAME
          return CATEGORY_NAME;
        case 3: // DATA_PATH
          return DATA_PATH;
        case 4: // DESCRIPTION
          return DESCRIPTION;
        case 5: // PARENT_ID
          return PARENT_ID;
        case 6: // ORDER_NUM
          return ORDER_NUM;
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
  private static final int __CATEGORYID_ISSET_ID = 0;
  private static final int __PARENTID_ISSET_ID = 1;
  private static final int __ORDERNUM_ISSET_ID = 2;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CATEGORY_ID, new org.apache.thrift.meta_data.FieldMetaData("categoryID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32        , "Integer")));
    tmpMap.put(_Fields.CATEGORY_NAME, new org.apache.thrift.meta_data.FieldMetaData("categoryName", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DATA_PATH, new org.apache.thrift.meta_data.FieldMetaData("dataPath", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.DESCRIPTION, new org.apache.thrift.meta_data.FieldMetaData("description", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.PARENT_ID, new org.apache.thrift.meta_data.FieldMetaData("parentID", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ORDER_NUM, new org.apache.thrift.meta_data.FieldMetaData("orderNum", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcResourceCategory.class, metaDataMap);
  }

  public RpcResourceCategory() {
  }

  public RpcResourceCategory(
    int categoryID,
    java.lang.String categoryName,
    java.lang.String dataPath,
    java.lang.String description,
    int parentID,
    int orderNum)
  {
    this();
    this.categoryID = categoryID;
    setCategoryIDIsSet(true);
    this.categoryName = categoryName;
    this.dataPath = dataPath;
    this.description = description;
    this.parentID = parentID;
    setParentIDIsSet(true);
    this.orderNum = orderNum;
    setOrderNumIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcResourceCategory(RpcResourceCategory other) {
    __isset_bitfield = other.__isset_bitfield;
    this.categoryID = other.categoryID;
    if (other.isSetCategoryName()) {
      this.categoryName = other.categoryName;
    }
    if (other.isSetDataPath()) {
      this.dataPath = other.dataPath;
    }
    if (other.isSetDescription()) {
      this.description = other.description;
    }
    this.parentID = other.parentID;
    this.orderNum = other.orderNum;
  }

  public RpcResourceCategory deepCopy() {
    return new RpcResourceCategory(this);
  }

  @Override
  public void clear() {
    setCategoryIDIsSet(false);
    this.categoryID = 0;
    this.categoryName = null;
    this.dataPath = null;
    this.description = null;
    setParentIDIsSet(false);
    this.parentID = 0;
    setOrderNumIsSet(false);
    this.orderNum = 0;
  }

  public int getCategoryID() {
    return this.categoryID;
  }

  public void setCategoryID(int categoryID) {
    this.categoryID = categoryID;
    setCategoryIDIsSet(true);
  }

  public void unsetCategoryID() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __CATEGORYID_ISSET_ID);
  }

  /** Returns true if field categoryID is set (has been assigned a value) and false otherwise */
  public boolean isSetCategoryID() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __CATEGORYID_ISSET_ID);
  }

  public void setCategoryIDIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __CATEGORYID_ISSET_ID, value);
  }

  public java.lang.String getCategoryName() {
    return this.categoryName;
  }

  public void setCategoryName(java.lang.String categoryName) {
    this.categoryName = categoryName;
  }

  public void unsetCategoryName() {
    this.categoryName = null;
  }

  /** Returns true if field categoryName is set (has been assigned a value) and false otherwise */
  public boolean isSetCategoryName() {
    return this.categoryName != null;
  }

  public void setCategoryNameIsSet(boolean value) {
    if (!value) {
      this.categoryName = null;
    }
  }

  public java.lang.String getDataPath() {
    return this.dataPath;
  }

  public void setDataPath(java.lang.String dataPath) {
    this.dataPath = dataPath;
  }

  public void unsetDataPath() {
    this.dataPath = null;
  }

  /** Returns true if field dataPath is set (has been assigned a value) and false otherwise */
  public boolean isSetDataPath() {
    return this.dataPath != null;
  }

  public void setDataPathIsSet(boolean value) {
    if (!value) {
      this.dataPath = null;
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

  public int getParentID() {
    return this.parentID;
  }

  public void setParentID(int parentID) {
    this.parentID = parentID;
    setParentIDIsSet(true);
  }

  public void unsetParentID() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __PARENTID_ISSET_ID);
  }

  /** Returns true if field parentID is set (has been assigned a value) and false otherwise */
  public boolean isSetParentID() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __PARENTID_ISSET_ID);
  }

  public void setParentIDIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __PARENTID_ISSET_ID, value);
  }

  public int getOrderNum() {
    return this.orderNum;
  }

  public void setOrderNum(int orderNum) {
    this.orderNum = orderNum;
    setOrderNumIsSet(true);
  }

  public void unsetOrderNum() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __ORDERNUM_ISSET_ID);
  }

  /** Returns true if field orderNum is set (has been assigned a value) and false otherwise */
  public boolean isSetOrderNum() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __ORDERNUM_ISSET_ID);
  }

  public void setOrderNumIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __ORDERNUM_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CATEGORY_ID:
      if (value == null) {
        unsetCategoryID();
      } else {
        setCategoryID((java.lang.Integer)value);
      }
      break;

    case CATEGORY_NAME:
      if (value == null) {
        unsetCategoryName();
      } else {
        setCategoryName((java.lang.String)value);
      }
      break;

    case DATA_PATH:
      if (value == null) {
        unsetDataPath();
      } else {
        setDataPath((java.lang.String)value);
      }
      break;

    case DESCRIPTION:
      if (value == null) {
        unsetDescription();
      } else {
        setDescription((java.lang.String)value);
      }
      break;

    case PARENT_ID:
      if (value == null) {
        unsetParentID();
      } else {
        setParentID((java.lang.Integer)value);
      }
      break;

    case ORDER_NUM:
      if (value == null) {
        unsetOrderNum();
      } else {
        setOrderNum((java.lang.Integer)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case CATEGORY_ID:
      return getCategoryID();

    case CATEGORY_NAME:
      return getCategoryName();

    case DATA_PATH:
      return getDataPath();

    case DESCRIPTION:
      return getDescription();

    case PARENT_ID:
      return getParentID();

    case ORDER_NUM:
      return getOrderNum();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case CATEGORY_ID:
      return isSetCategoryID();
    case CATEGORY_NAME:
      return isSetCategoryName();
    case DATA_PATH:
      return isSetDataPath();
    case DESCRIPTION:
      return isSetDescription();
    case PARENT_ID:
      return isSetParentID();
    case ORDER_NUM:
      return isSetOrderNum();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcResourceCategory)
      return this.equals((RpcResourceCategory)that);
    return false;
  }

  public boolean equals(RpcResourceCategory that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_categoryID = true;
    boolean that_present_categoryID = true;
    if (this_present_categoryID || that_present_categoryID) {
      if (!(this_present_categoryID && that_present_categoryID))
        return false;
      if (this.categoryID != that.categoryID)
        return false;
    }

    boolean this_present_categoryName = true && this.isSetCategoryName();
    boolean that_present_categoryName = true && that.isSetCategoryName();
    if (this_present_categoryName || that_present_categoryName) {
      if (!(this_present_categoryName && that_present_categoryName))
        return false;
      if (!this.categoryName.equals(that.categoryName))
        return false;
    }

    boolean this_present_dataPath = true && this.isSetDataPath();
    boolean that_present_dataPath = true && that.isSetDataPath();
    if (this_present_dataPath || that_present_dataPath) {
      if (!(this_present_dataPath && that_present_dataPath))
        return false;
      if (!this.dataPath.equals(that.dataPath))
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

    boolean this_present_parentID = true;
    boolean that_present_parentID = true;
    if (this_present_parentID || that_present_parentID) {
      if (!(this_present_parentID && that_present_parentID))
        return false;
      if (this.parentID != that.parentID)
        return false;
    }

    boolean this_present_orderNum = true;
    boolean that_present_orderNum = true;
    if (this_present_orderNum || that_present_orderNum) {
      if (!(this_present_orderNum && that_present_orderNum))
        return false;
      if (this.orderNum != that.orderNum)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + categoryID;

    hashCode = hashCode * 8191 + ((isSetCategoryName()) ? 131071 : 524287);
    if (isSetCategoryName())
      hashCode = hashCode * 8191 + categoryName.hashCode();

    hashCode = hashCode * 8191 + ((isSetDataPath()) ? 131071 : 524287);
    if (isSetDataPath())
      hashCode = hashCode * 8191 + dataPath.hashCode();

    hashCode = hashCode * 8191 + ((isSetDescription()) ? 131071 : 524287);
    if (isSetDescription())
      hashCode = hashCode * 8191 + description.hashCode();

    hashCode = hashCode * 8191 + parentID;

    hashCode = hashCode * 8191 + orderNum;

    return hashCode;
  }

  @Override
  public int compareTo(RpcResourceCategory other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetCategoryID()).compareTo(other.isSetCategoryID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCategoryID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.categoryID, other.categoryID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetCategoryName()).compareTo(other.isSetCategoryName());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCategoryName()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.categoryName, other.categoryName);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetDataPath()).compareTo(other.isSetDataPath());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDataPath()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dataPath, other.dataPath);
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
    lastComparison = java.lang.Boolean.valueOf(isSetParentID()).compareTo(other.isSetParentID());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetParentID()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.parentID, other.parentID);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetOrderNum()).compareTo(other.isSetOrderNum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetOrderNum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.orderNum, other.orderNum);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcResourceCategory(");
    boolean first = true;

    sb.append("categoryID:");
    sb.append(this.categoryID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("categoryName:");
    if (this.categoryName == null) {
      sb.append("null");
    } else {
      sb.append(this.categoryName);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("dataPath:");
    if (this.dataPath == null) {
      sb.append("null");
    } else {
      sb.append(this.dataPath);
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
    sb.append("parentID:");
    sb.append(this.parentID);
    first = false;
    if (!first) sb.append(", ");
    sb.append("orderNum:");
    sb.append(this.orderNum);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
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

  private static class RpcResourceCategoryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcResourceCategoryStandardScheme getScheme() {
      return new RpcResourceCategoryStandardScheme();
    }
  }

  private static class RpcResourceCategoryStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcResourceCategory> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcResourceCategory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // CATEGORY_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.categoryID = iprot.readI32();
              struct.setCategoryIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // CATEGORY_NAME
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.categoryName = iprot.readString();
              struct.setCategoryNameIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // DATA_PATH
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.dataPath = iprot.readString();
              struct.setDataPathIsSet(true);
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
          case 5: // PARENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.parentID = iprot.readI32();
              struct.setParentIDIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // ORDER_NUM
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.orderNum = iprot.readI32();
              struct.setOrderNumIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcResourceCategory struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(CATEGORY_ID_FIELD_DESC);
      oprot.writeI32(struct.categoryID);
      oprot.writeFieldEnd();
      if (struct.categoryName != null) {
        oprot.writeFieldBegin(CATEGORY_NAME_FIELD_DESC);
        oprot.writeString(struct.categoryName);
        oprot.writeFieldEnd();
      }
      if (struct.dataPath != null) {
        oprot.writeFieldBegin(DATA_PATH_FIELD_DESC);
        oprot.writeString(struct.dataPath);
        oprot.writeFieldEnd();
      }
      if (struct.description != null) {
        oprot.writeFieldBegin(DESCRIPTION_FIELD_DESC);
        oprot.writeString(struct.description);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(PARENT_ID_FIELD_DESC);
      oprot.writeI32(struct.parentID);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ORDER_NUM_FIELD_DESC);
      oprot.writeI32(struct.orderNum);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcResourceCategoryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcResourceCategoryTupleScheme getScheme() {
      return new RpcResourceCategoryTupleScheme();
    }
  }

  private static class RpcResourceCategoryTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcResourceCategory> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcResourceCategory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCategoryID()) {
        optionals.set(0);
      }
      if (struct.isSetCategoryName()) {
        optionals.set(1);
      }
      if (struct.isSetDataPath()) {
        optionals.set(2);
      }
      if (struct.isSetDescription()) {
        optionals.set(3);
      }
      if (struct.isSetParentID()) {
        optionals.set(4);
      }
      if (struct.isSetOrderNum()) {
        optionals.set(5);
      }
      oprot.writeBitSet(optionals, 6);
      if (struct.isSetCategoryID()) {
        oprot.writeI32(struct.categoryID);
      }
      if (struct.isSetCategoryName()) {
        oprot.writeString(struct.categoryName);
      }
      if (struct.isSetDataPath()) {
        oprot.writeString(struct.dataPath);
      }
      if (struct.isSetDescription()) {
        oprot.writeString(struct.description);
      }
      if (struct.isSetParentID()) {
        oprot.writeI32(struct.parentID);
      }
      if (struct.isSetOrderNum()) {
        oprot.writeI32(struct.orderNum);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcResourceCategory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(6);
      if (incoming.get(0)) {
        struct.categoryID = iprot.readI32();
        struct.setCategoryIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.categoryName = iprot.readString();
        struct.setCategoryNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.dataPath = iprot.readString();
        struct.setDataPathIsSet(true);
      }
      if (incoming.get(3)) {
        struct.description = iprot.readString();
        struct.setDescriptionIsSet(true);
      }
      if (incoming.get(4)) {
        struct.parentID = iprot.readI32();
        struct.setParentIDIsSet(true);
      }
      if (incoming.get(5)) {
        struct.orderNum = iprot.readI32();
        struct.setOrderNumIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

