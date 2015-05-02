package com.flol.semrankercommon.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="keyword_scan_summary")
public class KeywordScanSummary extends BaseDomain implements Serializable{

	private static final long serialVersionUID = 8681105987368052096L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
    @JoinColumn(name = "keyword_search_engine_account_domain", referencedColumnName = "id", nullable = false)
	private KeywordSearchengineAccountDomain keywordSearchengineAccountDomain;
	
	private Date date;
	
	@ManyToOne
    @JoinColumn(name = "keyword_scan_summary_status", referencedColumnName = "id", nullable = false)
	private KeywordScanSummaryStatus keywordScanSummaryStatus;
	
	@ManyToOne
    @JoinColumn(name = "proxy", referencedColumnName = "id", nullable = true)
	private Proxy proxy;
	
	@Lob
	@Column(name = "cache_page")
	@Basic(fetch=FetchType.LAZY)
	private byte[] cachePage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public KeywordSearchengineAccountDomain getKeywordSearchengineAccountDomain() {
		return keywordSearchengineAccountDomain;
	}

	public void setKeywordSearchengineAccountDomain(
			KeywordSearchengineAccountDomain keywordSearchengineAccountDomain) {
		this.keywordSearchengineAccountDomain = keywordSearchengineAccountDomain;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public KeywordScanSummaryStatus getKeywordScanSummaryStatus() {
		return keywordScanSummaryStatus;
	}

	public void setKeywordScanSummaryStatus(
			KeywordScanSummaryStatus keywordScanSummaryStatus) {
		this.keywordScanSummaryStatus = keywordScanSummaryStatus;
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KeywordScanSummary other = (KeywordScanSummary) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public byte[] getCachePage() {
		return cachePage;
	}

	public void setCachePage(byte[] cachePage) {
		this.cachePage = cachePage;
	}
	
	

}
