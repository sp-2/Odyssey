package com.example.odyssey.models;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="piano")
public class Piano {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Size(min=2, max=240, message="Name must be greater than 2 characters")
    private String title;
    
    @NotNull
    //@Size(min=2, message="Description must be greater than 2 characters")
    @Column(length = 3000)
    private String description;
    
    @NotNull
    @Size(min=2, max=240, message="Make must be greater than 2 characters")
    private String make;
    
    @NotNull
    @Size(min=1, max=240, message="Model must exist")
    private String model;
    
	@NotNull
	private double price;
    
    private String digital;

	@NotNull
	private String rent_sale;
    
	private String grand_upright;
	
	private String category;
	
	private int quantity;
	
    @Column(length = 5000)
    private String info;
    
    @Column(length = 5000)
    private String spec;
    
    @Column(length = 5000)
    private String info2;
    
    private int total_quantity;
    
    private int total_quantity_sold;
    
    //private double avg_ratings;
	    
	@Transient
	private MultipartFile pianoimages;
	
	@Transient
	private MultipartFile pianovideos;
	
	@Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;	
	
	@OneToMany(mappedBy="piano", fetch = FetchType.LAZY)
    private List<Rating> ratings;
    	 
    public Piano() {
    }
    
	public Piano(String title, String description, String make) {
		this.title       = title;
		this.description = description;
		this.make        = make;
	}
	
	public Piano(String title, String description, String make, double price) {
		this.title       = title;
		this.description = description;
		this.make        = make;
		this.price       = price;
	}

	public Piano(String title, String description, String make, double price, String digital) {
		this.title       = title;
		this.description = description;
		this.make        = make;
		this.price       = price;
		this.digital     = digital;
	}

	public Piano(String title, String description, String make, double price, String digital, String rent_sale) {
		this.title       = title;
		this.description = description;
		this.make        = make;
		this.price       = price;
		this.digital     = digital;
		this.rent_sale   = rent_sale;
	}

    public Piano(String title, String description, String make, String model,
			double price, String digital, String rent_sale,
			String category, int quantity) {

		this.title       = title;
		this.description = description;
		this.make        = make;
		this.model       = model;
		this.price       = price;
		this.digital     = digital;
		this.rent_sale   = rent_sale;
		this.category    = category;
		this.quantity    = quantity;
	}

	public Piano(String title,
			String description, String info, String spec, String info2, String make, String model,
			double price, String digital, String rent_sale, String grand_upright, String category, int quantity) {
		this.title         = title;
		this.description   = description;
		this.info          = info;
		this.spec          = spec;
		this.info2         = info2;
		this.make          = make;
		this.model         = model;
		this.price         = price;
		this.digital       = digital;
		this.rent_sale     = rent_sale;
		this.grand_upright = grand_upright;
		this.category      = category;
		this.quantity      = quantity;
	}
	
	 public List<Rating> getRatings() {
			return ratings;
	 }

	 public void setRatings(List<Rating> ratings) {
	    	this.ratings = ratings;
	 }
		
    public int getTotal_quantity() {
		return total_quantity;
	}

	public void setTotal_quantity(int total_quantity) {
		this.total_quantity = total_quantity;
	}

	public int getTotal_quantity_sold() {
		return total_quantity_sold;
	}

	public void setTotal_quantity_sold(int total_quantity_sold) {
		this.total_quantity_sold = total_quantity_sold;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}

	public String getGrand_upright() {
		return grand_upright;
	}

	public void setGrand_upright(String grand_upright) {
		this.grand_upright = grand_upright;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
    public String getDigital() {
		return digital;
	}

	public void setDigital(String digital) {
		this.digital = digital;
	}

	public MultipartFile getPianoimages() {
		return pianoimages;
	}

	public void setPianoimages(MultipartFile pianoimages) {
		this.pianoimages = pianoimages;
	}

	public MultipartFile getPianovideos() {
		return pianovideos;
	}

	public void setPianovideos(MultipartFile pianovideos) {
		this.pianovideos = pianovideos;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRent_sale() {
		return rent_sale;
	}

	public void setRent_sale(String rent_sale) {
		this.rent_sale = rent_sale;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String avgRating() {
		double sum = 0;
		for(Rating rating: ratings) {
			sum += rating.getValue();
		}
		if (ratings.size() == 0) {
			return "0";
		}
		String result = String.format("%.1f", sum/ratings.size());
		return result;
    }
	
	public double avgRatingVal() {
		double sum = 0;
		for(Rating rating: ratings) {
			sum += rating.getValue();
		}
		if (ratings.size() == 0) {
			return 0;
		}
		
		return sum/ratings.size();
    }
	
	public int ratingsBar(int index) {
		int[] ratingBarVal = new int[5];
		
		for(Rating rating: ratings) {
			ratingBarVal[rating.getValue()-1]++;
		}
		
		return ratingBarVal[index];
    }
	
	public float ratingsBarPercentage(int index) {
		int[] ratingBarVal = new int[5];
		
		if (ratings.size() == 0) {
			return 0;
		}
		
		for(Rating rating: ratings) {
			ratingBarVal[rating.getValue()-1]++;
		}

		float val = ((float)ratingBarVal[index]/ratings.size())*100;
		//return (int)(ratingBarVal[index]/ratings.size())*100;

		return val;
    }
	
	public List<Rating> sortRating(){
		Collections.sort(ratings, Comparator.comparing(Rating::getUpdatedAt).reversed());
		return ratings;
    }
	
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}

