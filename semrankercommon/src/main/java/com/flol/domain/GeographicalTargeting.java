package com.flol.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the geographical_targeting database table.
 * 
 */
@Entity
@Table(name="geographical_targeting")
@NamedQuery(name="GeographicalTargeting.findAll", query="SELECT g FROM GeographicalTargeting g")
public class GeographicalTargeting extends BaseDomain implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="canonical_name")
	private String canonicalName;

	@Column(name="country_code")
	private String countryCode;

	private String name;

	@Column(name="parent_id")
	private int parentId;

	@Column(name="target_type")
	private String targetType;

	private String uule;

	public GeographicalTargeting() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCanonicalName() {
		return this.canonicalName;
	}

	public void setCanonicalName(String canonicalName) {
		this.canonicalName = canonicalName;
	}

	public String getCountryCode() {
		return this.countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getParentId() {
		return this.parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getTargetType() {
		return this.targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getUule() {
		return this.uule;
	}

	public void setUule(String uule) {
		this.uule = uule;
	}

}