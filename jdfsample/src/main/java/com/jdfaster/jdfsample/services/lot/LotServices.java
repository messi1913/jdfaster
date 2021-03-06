package com.jdfaster.jdfsample.services.lot;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jdfaster.jdfsample.services.lot.create.CreateLot;
import com.jdfaster.jdfsample.services.lot.create.CreateLotIn;
import com.jdfaster.jdfsample.services.lot.create.CreateLotOut;
import com.jdfaster.jdfsample.services.lot.end.EndLot;
import com.jdfaster.jdfsample.services.lot.end.EndLotIn;
import com.jdfaster.jdfsample.services.lot.end.EndLotOut;
import com.jdfaster.jdfsample.services.lot.get_id_list.GetLotIdList;
import com.jdfaster.jdfsample.services.lot.get_id_list.GetLotIdListIn;
import com.jdfaster.jdfsample.services.lot.get_id_list.GetLotIdListOut;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotList;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotListIn;
import com.jdfaster.jdfsample.services.lot.get_list.GetLotListOut;
import com.jdfaster.jdfsample.services.lot.get_size.GetLotSize;
import com.jdfaster.jdfsample.services.lot.get_size.GetLotSizeIn;
import com.jdfaster.jdfsample.services.lot.get_size.GetLotSizeOut;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLot;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLotIn;
import com.jdfaster.jdfsample.services.lot.instore.InstoreLotOut;
import com.jdfaster.jdfsample.services.lot.pack.PackLot;
import com.jdfaster.jdfsample.services.lot.pack.PackLotIn;
import com.jdfaster.jdfsample.services.lot.pack.PackLotOut;
import com.jdfaster.jdfsample.services.lot.send.SendLot;
import com.jdfaster.jdfsample.services.lot.send.SendLotIn;
import com.jdfaster.jdfsample.services.lot.send.SendLotOut;
import com.jdfaster.jdfsample.services.lot.ship.ShipLot;
import com.jdfaster.jdfsample.services.lot.ship.ShipLotIn;
import com.jdfaster.jdfsample.services.lot.ship.ShipLotOut;

@RestController
@RequestMapping("/services/lot/")
@Transactional
public class LotServices {

	@RequestMapping(method = RequestMethod.POST, path = "/get_size/")
	public GetLotSizeOut getSize(@RequestBody GetLotSizeIn input) throws Exception {
		return new GetLotSize().getSize(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/get_list/")
	public GetLotListOut getList(@RequestBody GetLotListIn input) throws Exception {
		return new GetLotList().getList(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/get_id_list/")
	public GetLotIdListOut getIdList(@RequestBody GetLotIdListIn input) throws Exception {
		return new GetLotIdList().getIdList(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/instore/")
	public InstoreLotOut instore(@RequestBody InstoreLotIn input) throws Exception {
		return new InstoreLot().instore(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/send/")
	public SendLotOut send(@RequestBody SendLotIn input) throws Exception {
		return new SendLot().send(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/create/")
	public CreateLotOut create(@RequestBody CreateLotIn input) throws Exception {
		return new CreateLot().create(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/end/")
	public EndLotOut end(@RequestBody EndLotIn input) throws Exception {
		return new EndLot().end(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/get_pack/")
	public GetLotIdListOut getPack(@RequestBody GetLotIdListIn input) throws Exception {
		return new GetLotIdList().getIdList(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/pack/")
	public PackLotOut pack(@RequestBody PackLotIn input) throws Exception {
		return new PackLot().pack(input);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/ship/")
	public ShipLotOut ship(@RequestBody ShipLotIn input) throws Exception {
		return new ShipLot().ship(input);
	}
}
