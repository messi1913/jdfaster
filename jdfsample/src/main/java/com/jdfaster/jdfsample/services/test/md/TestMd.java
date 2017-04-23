package com.jdfaster.jdfsample.services.test.md;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.jdfaster.jdfsample.services.flow.MesFlow;
import com.jdfaster.jdfsample.services.flow.MesFlowOper;
import com.jdfaster.jdfsample.services.loc.MesLoc;
import com.jdfaster.jdfsample.services.mat.MesMat;
import com.jdfaster.jdfsample.services.mat.MesMatComp;
import com.jdfaster.jdfsample.services.oper.MesOper;
import com.jdfaster.jdfsample.services.test.utils.MesTestUtils;

public class TestMd {

	public TestMdOut md(TestMdIn input, EntityManager em) throws Exception {

		String flowCode = "F-MP001";

		// Flow
		{
			MesFlow flow = new MesFlow();
			flow.setFlowCode(flowCode);
			flow.setFlowName("Mobile Phone Flow");
			em.persist(flow);
		}

		// Oper
		{
			List<MesOper> opers = new ArrayList<MesOper>();
			List<MesFlowOper> flowOpers = new ArrayList<MesFlowOper>();

			int i = 0;
			{
				MesOper oper = new MesOper();
				oper.setOperCode("O-ASSY");
				oper.setOperName("Assembly Oper");
				opers.add(oper);
				em.persist(oper);
				MesFlowOper flowOper = new MesFlowOper();
				flowOper.setSeqNo(++i);
				flowOper.setFlowCode(flowCode);
				flowOper.setOperCode("O-ASSY");
				flowOpers.add(flowOper);
				em.persist(flowOper);
			}

			{
				MesOper oper = new MesOper();
				oper.setOperCode("O-TEST");
				oper.setOperName("Test Oper");
				opers.add(oper);
				em.persist(oper);
				MesFlowOper flowOper = new MesFlowOper();
				flowOper.setSeqNo(++i);
				flowOper.setFlowCode(flowCode);
				flowOper.setOperCode("O-TEST");
				flowOpers.add(flowOper);
				em.persist(flowOper);
			}

			{
				MesOper oper = new MesOper();
				oper.setOperCode("O-PACK");
				oper.setOperName("Packing Oper");
				opers.add(oper);
				em.persist(oper);

				MesFlowOper flowOper = new MesFlowOper();
				flowOper.setSeqNo(++i);
				flowOper.setFlowCode(flowCode);
				flowOper.setOperCode("O-PACK");
				flowOpers.add(flowOper);
				em.persist(flowOper);
			}

			{
				MesOper oper = new MesOper();
				oper.setOperCode("O-SHIP");
				oper.setOperName("Shipping Oper");
				opers.add(oper);
				em.persist(oper);

				MesFlowOper flowOper = new MesFlowOper();
				flowOper.setSeqNo(++i);
				flowOper.setFlowCode(flowCode);
				flowOper.setOperCode("O-SHIP");
				flowOpers.add(flowOper);
				em.persist(flowOper);
			}

		}

		// Store
		{
			List<MesLoc> stores = new ArrayList<MesLoc>();

			{
				MesLoc store = new MesLoc();
				store.setLocCode("S-RM");
				store.setLocName("Raw Material Store");
				store.setLocType("STORE");
				stores.add(store);
				em.persist(store);
			}

			{
				MesLoc store = new MesLoc();
				store.setLocCode("S-FG");
				store.setLocName("Final Goods Store");
				store.setLocType("STORE");
				stores.add(store);
				em.persist(store);
			}
		}

		// Line
		{
			List<MesLoc> lines = new ArrayList<MesLoc>();

			for (String lineCode : MesTestUtils.getAllLineCodes()) {
				MesLoc line = new MesLoc();
				line.setLocCode(lineCode);
				line.setLocName("Line " + lineCode.substring(2));
				line.setLocType("LINE");
				lines.add(line);
				em.persist(line);
			}
		}

		String matCode = "M-GS8";

		// Product
		{
			MesMat prod = new MesMat();
			prod.setMatCode(matCode);
			prod.setMatName("Galaxy S8");
			prod.setMatType("FG");
			prod.setFlowCode(flowCode);
			em.persist(prod);
		}

		// Component
		{
			List<MesMat> comps = new ArrayList<MesMat>();
			List<MesMatComp> matComps = new ArrayList<MesMatComp>();

			{
				MesMat comp = new MesMat();
				comp.setMatCode("M-PCBA-GS8");
				comp.setMatName("PCBA for Galaxy S8");
				comp.setMatType("SG");
				comps.add(comp);
				em.persist(comp);

				MesMatComp matComp = new MesMatComp();
				matComp.setMatCode(matCode);
				matComp.setCompMatCode("M-PCBA-GS8");
				matComp.setCompMatQty(1);
				matComp.setOperCode("O-ASSY");
				matComps.add(matComp);
				em.persist(matComp);
			}

			{
				MesMat comp = new MesMat();
				comp.setMatCode("M-OLED-568ppi");
				comp.setMatName("OLED 568ppi");
				comp.setMatType("RM");
				em.persist(comp);

				MesMatComp matComp = new MesMatComp();
				matComp.setMatCode(matCode);
				matComp.setCompMatCode("M-OLED-568ppi");
				matComp.setCompMatQty(1);
				matComp.setOperCode("O-ASSY");
				matComps.add(matComp);
				em.persist(matComp);
			}

			{
				MesMat comp = new MesMat();
				comp.setMatCode("M-B-3000mAh");
				comp.setMatName("Battery 300mAh");
				comp.setMatType("RM");
				em.persist(comp);

				MesMatComp matComp = new MesMatComp();
				matComp.setMatCode(matCode);
				matComp.setCompMatCode("M-B-3000mAh");
				matComp.setCompMatQty(1);
				matComp.setOperCode("O-ASSY");
				matComps.add(matComp);
				em.persist(matComp);
			}

			{
				MesMat comp = new MesMat();
				comp.setMatCode("M-EP-TA20KPK");
				comp.setMatName("Fast Charging Adapter");
				comp.setMatType("RM");
				em.persist(comp);

				MesMatComp matComp = new MesMatComp();
				matComp.setMatCode(matCode);
				matComp.setCompMatCode("M-EP-TA20KPK");
				matComp.setCompMatQty(1);
				matComp.setOperCode("O-ASSY");
				matComps.add(matComp);
				em.persist(matComp);
			}
		}

		TestMdOut output = new TestMdOut();
		return output;
	}
}
