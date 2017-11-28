package com.anup.service;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.anup.entity.IPAddress;
import com.anup.entity.PickDirective;
import com.anup.repository.FacilityRepository;
import com.anup.repository.PDRepository;

import fr.w3blog.zpl.model.ZebraUtils;
import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class PDService {

	@Autowired
	private PDRepository repository;

	@Autowired
	private GenericTempService genericTempService;

	@Autowired
	private FacilityRepository facilityRepo;

	private List<IPAddress> addresses;

	private List<PickDirective> myList;

	public String username;

	public int port;

	@Autowired
	private GenericTempService gts;

	private String address;

	public List<PickDirective> getAllPicks() {

		return repository.findAllByDesc();

	}

	@PostConstruct
	@Scheduled(fixedDelay = 60000)
	@Transactional
	public void init() {

		address = gts.getAllDefIp();
		
		port = 9100;
		
		System.out.println("The IP address is :" + address);

		String zpl = "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + "^XA\r\n"
				+ "^MMT\r\n" + "^PW609\r\n" + "^LL0406\r\n" + "^LS0\r\n" + "^FO160,0^GFA,02304,02304,00036,:Z64:\r\n"
				+ "eJztks2O0zAUha+dMDGSoV4gESHTWvACZjVejRKVB/GOrUcaiUGKWvdHHXaw5HFSKmA7Ei8QmBdIxaaLDOCfLCq1G1j3SM61nOPPJzcGOOmkk/5f6cEkCrnBwsxCDjIu5kB8Eb1n4Ebjxp8fNVS9Z1IB99X4x6iGxKG8R2nrNoedYkX2OGcAuJ9Lt4lHdJnxvTAZeE5gSgVxs1vCZM+Do8dzjItC4+oY0UOO9hxhL8hEiGYIiSWKKCMare2F4wwWj5Mm5Dmf3XB1uW3HbVnz6vrd/a/t9q7+VmfwajNKjc+jwGIi5Asxs7YlhDzjQgm9XjiOWLx8n3vOFK0QLdiVWVuoKOdP6DXXukxcnnJT3rKQB+a+P0pYa4eUkJxKIvQYO06ysB92IQ9awgiYvFwjyDjP81RyY8obx3m+QbexP7CCh1ZK8dl9K/Ec4TjlMuSBT7E/6Ct6VDNjKpQmlOYsNUwXxQ4yXG7Qx7TPg0kjhCCAseMwIkBo9wbjmUtKYp6bJTdMGo6yxOdhkpmiyCF78OU74iz0By12VCkpB/NkSUnu2gO60UM4o7u7+aDxZ3VZ006HVVed16idjiZP67ez+7Z9U486s+mydbd/Me3P/ir6BMjWcCgEV71Hhp9eHPHg/hZHTnrUk8HrUFnobArHzkqsDVUFDk6OWAB+x1Ih/yWoO+o56aR/1l//soYs:0DC6\r\n"
				+ "^BY3,3,102^FT74,249^BCN,,Y,N\r\n" + "^FD>:%%CONT^FS\r\n"
				+ "^FT25,113^A0N,23,24^FH\\^FDWave number: %%Wave^FS\r\n"
				+ "^FT324,112^A0N,23,24^FH\\^FDDistro number: %%Distro^FS\r\n"
				+ "^FT20,334^A0N,23,24^FH\\^FDLocation Id: %%Location^FS\r\n"
				+ "^FT404,333^A0N,23,24^FH\\^FDPick Type: %%PT^FS\r\n"
				+ "^FT19,385^A0N,23,24^FH\\^FDDest Id: %%DEST^FS\r\n"
				+ "^FT404,386^A0N,23,24^FH\\^FDZone: %%ZONE^FS\r\n" + "^PQ1,0,1,Y^XZ";

		myList = getAllPicks();

		// List<String> containers = repository.findAllCont();

		System.out.println("Size of PickList is : " + myList.size());

		for (PickDirective p : myList) {

			zpl = zpl.replaceAll("%%CONT", p.getPICK_TO_CONTAINER_ID());

			zpl = zpl.replaceAll("%%Wave", p.getWAVE_NBR());

			zpl = zpl.replaceAll("%%Distro", p.getDISTRO_NBR());

			zpl = zpl.replaceAll("%%Location", p.getPICK_FROM_CONTAINER_ID());

			zpl = zpl.replaceAll("%%PT", p.getPICK_TYPE());

			zpl = zpl.replaceAll("%%DEST", p.getDEST_ID());

			zpl = zpl.replaceAll("%%ZONE", p.getZONE());

			System.out.println("The Zpl File is:");
			System.out.println(zpl);

			// updating pick directive
			repository.setPickDirectiveByContainer(p.getPICK_TO_CONTAINER_ID());

			System.out.println("List of values are: " + p.getITEM_ID() + "--" + p.getPICK_TO_CONTAINER_ID());

			// addresses = genericTempService.getAllAddress();

			// Iterator iter = addresses.iterator();
			//
			// Object first = iter.next();
			//
			// String ip = (String) first;

			try {
				ZebraUtils.printZpl(zpl, address, port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
