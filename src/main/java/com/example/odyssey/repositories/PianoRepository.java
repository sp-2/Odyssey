package com.example.odyssey.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Page;

import com.example.odyssey.models.Piano;

@Repository
public interface PianoRepository extends JpaRepository<Piano, Long> {
	
	List<Piano> findByMake(String make);
	
	Page<Piano> findByMake(String make,Pageable pageable);
	
	@Query("select u from Piano u where u.make = ?1 and u.model = ?2")
	List<Piano>  findAllPianosWithMake(String make, String model);
	
	@Query("select u from Piano u where u.rent_sale = ?1")
	List<Piano> findByRent_sale(String rent_sale);
	
	List<Piano> findByDigital(String digital);
	
	List<Piano> findByCategory(String new_used);
	
	//@Query(value="SELECT * FROM Piano WHERE make = ?1 AND rent_sale like %?2%", nativeQuery=true)
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0  order by u.createdAt asc")	
	List<Piano> findAll(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.createdAt asc")	
	List<Piano> findAll(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0  order by u.createdAt desc")	
	List<Piano> findAllSortByCreatedDesc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.createdAt desc")	
	List<Piano> findAllSortByCreatedDesc(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0 order by u.make asc")	
	List<Piano> findAllSortByMakeAsc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.make asc")	
	List<Piano> findAllSortByMakeAsc(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0 order by u.make desc")	
	List<Piano> findAllSortByMakeDesc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.make desc")	
	List<Piano> findAllSortByMakeDesc(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0 order by u.price asc")	
	List<Piano> findAllSortByPriceAsc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.price asc")	
	List<Piano> findAllSortByPriceAsc(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0 order by u.price desc")	
	List<Piano> findAllSortByPriceDesc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);

	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.price desc")	
	List<Piano> findAllSortByPriceDesc(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0 order by u.total_quantity_sold desc")	
	List<Piano> findAllSortByQuantitySoldDesc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.total_quantity_sold desc")	
	List<Piano> findAllSortByQuantitySoldDesc(String new_used, String make, String rent_sale, String digital, String grand_upright);
	////
	
	@Query("select u from Piano u where u.title like %?1% or u.make like %?1% or u.model like %?1% or u.category like %?1% or u.rent_sale like %?1% or u.grand_upright like %?1%")	
	List<Piano> findAllByKeyword(String keyword);
	
	
	//@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5% and u.quantity > 0 order by u.avg_ratings desc")	
	//List<Piano> findAllSortByRatingsDesc(String new_used, String make, String rent_sale, String digital, String grand_upright, boolean instock);
	
	//@Query("select u from Piano u where u.category like %?1% and u.make like %?2% and u.rent_sale like %?3% and u.digital like %?4% and u.grand_upright like %?5%  order by u.avg_ratings desc")	
	//List<Piano> findAllSortByRatingsDesc(String new_used, String make, String rent_sale, String digital, String grand_upright);
		
	//List<Piano> findByModel(String model);
    //List<Piano> findAll();
    Page<Piano> findAll(Pageable pageable);
}
