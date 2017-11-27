package com.anup.controller;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.anup.entity.ASN;
import com.anup.repository.AsnRepository;

import fr.w3blog.zpl.model.ZebraUtils;
import lombok.Getter;
import lombok.Setter;

@Component
@Scope("session")
@Getter
@Setter
public class AsnController {

	private String asn;

	private List<ASN> asnList = null;

	private List<String> containerList;

	private String newASN;

	String myASN = "";

	@Autowired
	private AsnRepository asnRepository;

	@PostConstruct
	// @Scheduled(initialDelay = 1000, fixedRate = 10000)
	public void init() {

		asnList = null;

	}

	public void changed(ValueChangeEvent e) throws IOException {
		newASN = e.getNewValue().toString();
	}

	public List<ASN> search(String newASN) {

		try {

			myASN = newASN.toLowerCase();

			if (myASN != null) {
				System.out.println("The value has been changed " + myASN);

			} else {
				System.out.println("Container ID is null dude!!!");
			}

			asnList = asnRepository.findAllAsn(myASN);

		} catch (Exception e) {
			e.getMessage();
		}

		return asnList;
	}

	public void print() {
		
		for (ASN n : asnList) {
			

			String s1 = "^XA~TA000~JSN^LT0^MNW^MTT^PON^PMN^LH0,0^JMA^PR6,6~SD15^JUS^LRN^CI0^XZ\r\n" + 
					"^XA\r\n" + 
					"^MMT\r\n" + 
					"^PW609\r\n" + 
					"^LL0406\r\n" + 
					"^LS0\r\n" + 
					"^FO224,0^GFA,01280,01280,00020,:Z64:\r\n" + 
					"eJztkTFOAzEQRf94ImxEtISKRXLIHsEgii0QbBUuQe6RIoqGKEoJV3KqXIJiJS5gOirCJhoj0VEilF+M5Kev57EMHPLvQoJBN38wBhJQ6GmA+S0nCwTgUpnDqkBrgAowyjzZc4q071llNZsTEd6z3HtkUNTekbKCBa0YfIT2LvfGFJEaYn+VHrJvheSCsHHX9dNCmcUM04Z6/mZI2We6Tctu1rXbao+2OC19Q/Qaht/7sbArdr5wLPleWnJpd75wgXHWLYxjMa6sIOrr0YZ9997+JJyt1Vfgbekh/PJcV5+6y2j9nuaIdtO/j7NR/NWnHPIX8wUgajEC:FDC9\r\n" + 
					"^BY3,3,102^FT74,249^BCN,,Y,N\r\n" + 
					"^FD>:%%CONT^FS\r\n" + 
					"^FT16,98^A0N,23,24^FH\\^FDASN number: %%ASN^FS\r\n" + 
					"^FT376,98^A0N,23,24^FH\\^FDAPPT number: %%APPT^FS\r\n" + 
					"^FT16,347^A0N,23,24^FH\\^FDPO number: %%PO^FS\r\n" + 
					"^PQ1,0,1,Y^XZ";
			
			s1 = s1.replaceAll("%%CONT", n.getContainer_id());
			
			s1 = s1.replaceAll("%%ASN", n.getAsn_nbr());
			
			s1 = s1.replaceAll("%%PO", n.getPo_nbr());
			
			s1 = s1.replaceAll("%%APPT", n.getAppt_nbr());
			
			System.out.println(s1);

			try {
				ZebraUtils.printZpl(s1, "192.168.120.23", 9100);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("The ASN LIST IS :" + n.getContainer_id() + " ---- " + n.getAsn_nbr());
		}

	}

}
