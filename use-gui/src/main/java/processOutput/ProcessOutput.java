package processOutput;

import jobCollection.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.tzi.use.api.UseApiException;
import org.tzi.use.api.UseSystemApi;
import org.tzi.use.main.Session;
import org.tzi.use.uml.ocl.value.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ProcessOutput {
    private UseSystemApi api;
    private JobCollectionFactory JCF;
    private String PATH_CASE;
    private String ROOT_PATH  = "F:/Vo Huong/KLTN/TTC/ttc21incrementalLabWorkflows/models";

    public ProcessOutput(Session session, String typeTest, int levelScale) {
        EPackage.Registry.INSTANCE.put(JobCollectionPackage.eNS_URI,
                JobCollectionPackage.eINSTANCE);
        this.api = UseSystemApi.create(session);
        this.JCF = JobCollectionFactory.eINSTANCE;
        this.PATH_CASE = String.format("%s/%s/%d/results/initialResult-RTL.xmi", ROOT_PATH, typeTest, levelScale);
    }

    public void writeXMI(JobCollection jobCollection) {
        XMIResource xmiResource = new XMIResourceImpl(
                URI.createFileURI(PATH_CASE));

        xmiResource.getContents().add(jobCollection);

        final Map<Object, Object> saveOptions = xmiResource.getDefaultSaveOptions();
        saveOptions.put(XMIResource.OPTION_SCHEMA_LOCATION,Boolean.TRUE);
        saveOptions.put(XMIResource.OPTION_ENCODING,"UTF-8");
        saveOptions.put(XMIResource.OPTION_USE_XMI_TYPE, Boolean.TRUE);
        saveOptions.put(XMIResource.OPTION_SAVE_TYPE_INFORMATION,Boolean.TRUE);


        try {
            xmiResource.save(saveOptions);
        } catch (Exception e){
            System.err.println("ERROR IN WRITE XMI: " + e.getMessage());
        }

    }

    public void report() throws UseApiException {
        JobCollection jc = JCF.createJobCollection();
        Map<String, EObject> OBJ_MAP = new HashMap<>();

        SetValue allLTJ = (SetValue) api.evaluate("LiquidTransferJob.allInstances()");
        SetValue allIJ = (SetValue) api.evaluate("IncubateJob.allInstances()");
        SetValue allWJ = (SetValue) api.evaluate("WashJob.allInstances()");
        SetValue allMP = (SetValue) api.evaluate("Microplate.allInstances()");
        SetValue allTR = (SetValue) api.evaluate("TubeRunner.allInstances()");
        SetValue allT = (SetValue) api.evaluate("Trough.allInstances()");

        Iterator<Value> itLTJ = allLTJ.iterator();
        Iterator<Value> itIJ = allIJ.iterator();
        Iterator<Value> itWJ = allWJ.iterator();
        Iterator<Value> itMP = allMP.iterator();
        Iterator<Value> itTR = allTR.iterator();
        Iterator<Value> itT = allT.iterator();

        while (itMP.hasNext()) {
            String nameObj = itMP.next().toString();

            String labwareName = ((StringValue) api.evaluate(String.format("%s.name", nameObj))).value();

            Microplate mp = JCF.createMicroplate();
            mp.setName(labwareName.trim());
            OBJ_MAP.put(nameObj, mp);
            jc.getLabware().add(mp);
        }

        while (itTR.hasNext()) {
            String nameObj = itTR.next().toString();

            String labwareName = ((StringValue) api.evaluate(String.format("%s.name", nameObj))).value();
            String barcodes = ((StringValue) api.evaluate(String.format("%s.barcodes", nameObj))).value();
            TubeRunner tr = JCF.createTubeRunner();
            tr.setName(labwareName.trim());
            tr.setBarcodesFlat(barcodes.trim());
            OBJ_MAP.put(nameObj, tr);
            jc.getLabware().add(tr);
        }

        while (itT.hasNext()) {
            String nameObj = itT.next().toString();

            String labwareName = ((StringValue) api.evaluate(String.format("%s.name", nameObj))).value();

            Trough t = JCF.createTrough();
            t.setName(labwareName.trim());
            OBJ_MAP.put(nameObj, t);
            jc.getLabware().add(t);
        }

        while (itIJ.hasNext()) {
            String nameObj = itIJ.next().toString();

            String stepName = ((StringValue) api.evaluate(String.format("%s.protocolStepName", nameObj))).value();
            Double temperature = Double.parseDouble(api.evaluate(String.format("%s.temperature", nameObj)).toString());
            Integer duration = Integer.parseInt(api.evaluate(String.format("%s.duration", nameObj)).toString());
            String mpName = api.evaluate(String.format("%s.tPlateI", nameObj)).toString();

            EObject mpObj = OBJ_MAP.get(mpName);

            IncubateJob incubateJob = JCF.createIncubateJob();
            incubateJob.setState(JobStatus.PLANNED);
            incubateJob.setProtocolStepName(stepName);
            incubateJob.setTemperature(temperature);
            incubateJob.setDuration(duration);
            incubateJob.setMicroplate((Microplate) mpObj);

            OBJ_MAP.put(nameObj, incubateJob);
            jc.getJobs().add(incubateJob);
        }

        while (itWJ.hasNext()) {
            String nameObj = itWJ.next().toString();

            String stepName = ((StringValue) api.evaluate(String.format("%s.protocolStepName", nameObj))).value();
            String cavities = ((StringValue) api.evaluate(String.format("%s.cavities", nameObj))).value();
            String prevObjName = ((SetValue) api.evaluate(String.format("%s.previous", nameObj)))
                    .asSequence().get(0).toString();
            String mpName = api.evaluate(String.format("%s.tPlateW", nameObj)).toString();

            EObject prevObj = OBJ_MAP.get(prevObjName);
            EObject mpObj = OBJ_MAP.get(mpName);

            WashJob washJob = JCF.createWashJob();
            washJob.setState(JobStatus.PLANNED);
            washJob.setProtocolStepName(stepName);
            washJob.setCavitiesFlat(cavities.trim());
            washJob.setMicroplate((Microplate) mpObj);
            washJob.getPrevious().add((IncubateJob) prevObj);

            ((IncubateJob) prevObj).getNext().add(washJob);

            OBJ_MAP.put(nameObj, washJob);
            jc.getJobs().add(washJob);
        }


        while (itLTJ.hasNext()) {
            String nameObj = itLTJ.next().toString();

            String stepName = ((StringValue) api.evaluate(String.format("%s.protocolStepName", nameObj))).value();
            String source = api.evaluate(String.format("%s.source", nameObj)).toString();
            String target = api.evaluate(String.format("%s.target", nameObj)).toString();
            SequenceValue previous = ((SequenceValue) api.evaluate(String.format("%s.previous->asSequence()", nameObj)));
            SequenceValue next = ((SequenceValue) api.evaluate(String.format("%s.next->asSequence()", nameObj)));
            SequenceValue tips = ((SequenceValue) api.evaluate(String.format("%s.tips->asSequence()", nameObj)));

            Labware srcLabware = (Labware) OBJ_MAP.get(source);
            Labware trgLabware = (Labware) OBJ_MAP.get(target);

            LiquidTransferJob liquidTransferJob = JCF.createLiquidTransferJob();
            liquidTransferJob.setState(JobStatus.PLANNED);
            liquidTransferJob.setProtocolStepName(stepName);
            liquidTransferJob.setSource(srcLabware);
            liquidTransferJob.setTarget(trgLabware);


            for (int i = 0; i < tips.size(); i++) {
                String tipName = tips.get(i).toString();
//                System.out.println(tipName);

                Integer trgCavityIndex = Integer.parseInt(api.evaluate(String.format("%s.targetCavityIndex", tipName)).toString()) - 1;
                Double volume =  Double.parseDouble(api.evaluate(String.format("%s.volume", tipName)).toString());
                TipLiquidTransfer tip = JCF.createTipLiquidTransfer();
                tip.setStatus(JobStatus.PLANNED);
                tip.setTargetCavityIndex(trgCavityIndex);
                tip.setVolume(volume);

                if(stepName.startsWith("AddSample")) {
                    Integer srcCavityIndex = Integer.parseInt(api.evaluate(String.format("%s.sourceCavityIndex", tipName)).toString()) - 1;
                    tip.setSourceCavityIndex(srcCavityIndex);
                }

                liquidTransferJob.getTips().add(tip);
            }


            if (previous.size() != 0) {
                EObject prevObj = OBJ_MAP.get(previous.get(0).toString());
                String type = previous.get(0).type().toString();

                if("WashJob".equals(type)) {
                    liquidTransferJob.getPrevious().add((WashJob) prevObj);
                    ((WashJob) prevObj).getNext().add(liquidTransferJob);
                } else if("IncubateJob".equals(type)){
                    liquidTransferJob.getPrevious().add((IncubateJob) prevObj);
                    ((IncubateJob) prevObj).getNext().add(liquidTransferJob);
                }
            }

            if (next.size() != 0) {
                EObject nextObj = OBJ_MAP.get(next.get(0).toString());

                liquidTransferJob.getNext().add((IncubateJob) nextObj);
                ((IncubateJob) nextObj).getPrevious().add(liquidTransferJob);
            }

            jc.getJobs().add(liquidTransferJob);
        }

        writeXMI(jc);
    }
}
