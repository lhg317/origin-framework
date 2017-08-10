/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.goldgov.origin.modules.basedata.api;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-08-07")
public class RpcBaseDataCategory implements org.apache.thrift.TBase<RpcBaseDataCategory, RpcBaseDataCategory._Fields>, java.io.Serializable, Cloneable, Comparable<RpcBaseDataCategory> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("RpcBaseDataCategory");

  private static final org.apache.thrift.protocol.TField CATEGORY_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("categoryID", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CATEGORY_NAME_FIELD_DESC = new org.apache.thrift.protocol.TField("categoryName", org.apache.thrift.protocol.TType.STRING, (short)2);
  private static final org.apache.thrift.protocol.TField CATEGORY_CODE_FIELD_DESC = new org.apache.thrift.protocol.TField("categoryCode", org.apache.thrift.protocol.TType.STRING, (short)3);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new RpcBaseDataCategoryStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new RpcBaseDataCategoryTupleSchemeFactory();

  private java.lang.String categoryID; // optional
  private java.lang.String categoryName; // optional
  private java.lang.String categoryCode; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    CATEGORY_ID((short)1, "categoryID"),
    CATEGORY_NAME((short)2, "categoryName"),
    CATEGORY_CODE((short)3, "categoryCode");

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
        case 3: // CATEGORY_CODE
          return CATEGORY_CODE;
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
  private static final _Fields optionals[] = {_Fields.CATEGORY_ID,_Fields.CATEGORY_NAME,_Fields.CATEGORY_CODE};
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.CATEGORY_ID, new org.apache.thrift.meta_data.FieldMetaData("categoryID", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CATEGORY_NAME, new org.apache.thrift.meta_data.FieldMetaData("categoryName", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CATEGORY_CODE, new org.apache.thrift.meta_data.FieldMetaData("categoryCode", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(RpcBaseDataCategory.class, metaDataMap);
  }

  public RpcBaseDataCategory() {
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public RpcBaseDataCategory(RpcBaseDataCategory other) {
    if (other.isSetCategoryID()) {
      this.categoryID = other.categoryID;
    }
    if (other.isSetCategoryName()) {
      this.categoryName = other.categoryName;
    }
    if (other.isSetCategoryCode()) {
      this.categoryCode = other.categoryCode;
    }
  }

  public RpcBaseDataCategory deepCopy() {
    return new RpcBaseDataCategory(this);
  }

  @Override
  public void clear() {
    this.categoryID = null;
    this.categoryName = null;
    this.categoryCode = null;
  }

  public java.lang.String getCategoryID() {
    return this.categoryID;
  }

  public void setCategoryID(java.lang.String categoryID) {
    this.categoryID = categoryID;
  }

  public void unsetCategoryID() {
    this.categoryID = null;
  }

  /** Returns true if field categoryID is set (has been assigned a value) and false otherwise */
  public boolean isSetCategoryID() {
    return this.categoryID != null;
  }

  public void setCategoryIDIsSet(boolean value) {
    if (!value) {
      this.categoryID = null;
    }
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

  public java.lang.String getCategoryCode() {
    return this.categoryCode;
  }

  public void setCategoryCode(java.lang.String categoryCode) {
    this.categoryCode = categoryCode;
  }

  public void unsetCategoryCode() {
    this.categoryCode = null;
  }

  /** Returns true if field categoryCode is set (has been assigned a value) and false otherwise */
  public boolean isSetCategoryCode() {
    return this.categoryCode != null;
  }

  public void setCategoryCodeIsSet(boolean value) {
    if (!value) {
      this.categoryCode = null;
    }
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case CATEGORY_ID:
      if (value == null) {
        unsetCategoryID();
      } else {
        setCategoryID((java.lang.String)value);
      }
      break;

    case CATEGORY_NAME:
      if (value == null) {
        unsetCategoryName();
      } else {
        setCategoryName((java.lang.String)value);
      }
      break;

    case CATEGORY_CODE:
      if (value == null) {
        unsetCategoryCode();
      } else {
        setCategoryCode((java.lang.String)value);
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

    case CATEGORY_CODE:
      return getCategoryCode();

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
    case CATEGORY_CODE:
      return isSetCategoryCode();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof RpcBaseDataCategory)
      return this.equals((RpcBaseDataCategory)that);
    return false;
  }

  public boolean equals(RpcBaseDataCategory that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_categoryID = true && this.isSetCategoryID();
    boolean that_present_categoryID = true && that.isSetCategoryID();
    if (this_present_categoryID || that_present_categoryID) {
      if (!(this_present_categoryID && that_present_categoryID))
        return false;
      if (!this.categoryID.equals(that.categoryID))
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

    boolean this_present_categoryCode = true && this.isSetCategoryCode();
    boolean that_present_categoryCode = true && that.isSetCategoryCode();
    if (this_present_categoryCode || that_present_categoryCode) {
      if (!(this_present_categoryCode && that_present_categoryCode))
        return false;
      if (!this.categoryCode.equals(that.categoryCode))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + ((isSetCategoryID()) ? 131071 : 524287);
    if (isSetCategoryID())
      hashCode = hashCode * 8191 + categoryID.hashCode();

    hashCode = hashCode * 8191 + ((isSetCategoryName()) ? 131071 : 524287);
    if (isSetCategoryName())
      hashCode = hashCode * 8191 + categoryName.hashCode();

    hashCode = hashCode * 8191 + ((isSetCategoryCode()) ? 131071 : 524287);
    if (isSetCategoryCode())
      hashCode = hashCode * 8191 + categoryCode.hashCode();

    return hashCode;
  }

  @Override
  public int compareTo(RpcBaseDataCategory other) {
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
    lastComparison = java.lang.Boolean.valueOf(isSetCategoryCode()).compareTo(other.isSetCategoryCode());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCategoryCode()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.categoryCode, other.categoryCode);
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
    java.lang.StringBuilder sb = new java.lang.StringBuilder("RpcBaseDataCategory(");
    boolean first = true;

    if (isSetCategoryID()) {
      sb.append("categoryID:");
      if (this.categoryID == null) {
        sb.append("null");
      } else {
        sb.append(this.categoryID);
      }
      first = false;
    }
    if (isSetCategoryName()) {
      if (!first) sb.append(", ");
      sb.append("categoryName:");
      if (this.categoryName == null) {
        sb.append("null");
      } else {
        sb.append(this.categoryName);
      }
      first = false;
    }
    if (isSetCategoryCode()) {
      if (!first) sb.append(", ");
      sb.append("categoryCode:");
      if (this.categoryCode == null) {
        sb.append("null");
      } else {
        sb.append(this.categoryCode);
      }
      first = false;
    }
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
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class RpcBaseDataCategoryStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcBaseDataCategoryStandardScheme getScheme() {
      return new RpcBaseDataCategoryStandardScheme();
    }
  }

  private static class RpcBaseDataCategoryStandardScheme extends org.apache.thrift.scheme.StandardScheme<RpcBaseDataCategory> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, RpcBaseDataCategory struct) throws org.apache.thrift.TException {
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
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.categoryID = iprot.readString();
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
          case 3: // CATEGORY_CODE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.categoryCode = iprot.readString();
              struct.setCategoryCodeIsSet(true);
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

    public void write(org.apache.thrift.protocol.TProtocol oprot, RpcBaseDataCategory struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.categoryID != null) {
        if (struct.isSetCategoryID()) {
          oprot.writeFieldBegin(CATEGORY_ID_FIELD_DESC);
          oprot.writeString(struct.categoryID);
          oprot.writeFieldEnd();
        }
      }
      if (struct.categoryName != null) {
        if (struct.isSetCategoryName()) {
          oprot.writeFieldBegin(CATEGORY_NAME_FIELD_DESC);
          oprot.writeString(struct.categoryName);
          oprot.writeFieldEnd();
        }
      }
      if (struct.categoryCode != null) {
        if (struct.isSetCategoryCode()) {
          oprot.writeFieldBegin(CATEGORY_CODE_FIELD_DESC);
          oprot.writeString(struct.categoryCode);
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class RpcBaseDataCategoryTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public RpcBaseDataCategoryTupleScheme getScheme() {
      return new RpcBaseDataCategoryTupleScheme();
    }
  }

  private static class RpcBaseDataCategoryTupleScheme extends org.apache.thrift.scheme.TupleScheme<RpcBaseDataCategory> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, RpcBaseDataCategory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetCategoryID()) {
        optionals.set(0);
      }
      if (struct.isSetCategoryName()) {
        optionals.set(1);
      }
      if (struct.isSetCategoryCode()) {
        optionals.set(2);
      }
      oprot.writeBitSet(optionals, 3);
      if (struct.isSetCategoryID()) {
        oprot.writeString(struct.categoryID);
      }
      if (struct.isSetCategoryName()) {
        oprot.writeString(struct.categoryName);
      }
      if (struct.isSetCategoryCode()) {
        oprot.writeString(struct.categoryCode);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, RpcBaseDataCategory struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(3);
      if (incoming.get(0)) {
        struct.categoryID = iprot.readString();
        struct.setCategoryIDIsSet(true);
      }
      if (incoming.get(1)) {
        struct.categoryName = iprot.readString();
        struct.setCategoryNameIsSet(true);
      }
      if (incoming.get(2)) {
        struct.categoryCode = iprot.readString();
        struct.setCategoryCodeIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}
