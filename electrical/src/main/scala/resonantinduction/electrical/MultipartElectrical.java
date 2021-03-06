package resonantinduction.electrical;

import resonantinduction.electrical.charger.PartCharger;
import resonantinduction.electrical.itemrailing.PartRailing;
import resonantinduction.electrical.levitator.PartLevitator;
import resonantinduction.electrical.multimeter.PartMultimeter;
import resonantinduction.electrical.transformer.PartTransformer;
import resonantinduction.electrical.wire.flat.PartFlatSwitchWire;
import resonantinduction.electrical.wire.flat.PartFlatWire;
import resonantinduction.electrical.wire.framed.PartFramedSwitchWire;
import resonantinduction.electrical.wire.framed.PartFramedWire;
import resonantinduction.quantum.gate.PartQuantumGlyph;
import codechicken.multipart.MultiPartRegistry;
import codechicken.multipart.MultiPartRegistry.IPartFactory;
import codechicken.multipart.MultipartGenerator;
import codechicken.multipart.TMultiPart;

public class MultipartElectrical implements IPartFactory
{
	public static MultipartElectrical INSTANCE;

	public static final String[] PART_TYPES = { "resonant_induction_quantum_glyph", "resonant_induction_wire", "resonant_induction_switch_wire", "resonant_induction_flat_wire", "resonant_induction_flat_switch_wire", "resonant_induction_multimeter", "resonant_induction_transformer", "resonant_induction_charger", "resonant_induction_levitator", "resonant_induction_itemrailing" };

	public MultipartElectrical()
	{
		MultiPartRegistry.registerParts(this, PART_TYPES);
		MultipartGenerator.registerPassThroughInterface("universalelectricity.api.electricity.IVoltageOutput");
		MultipartGenerator.registerTrait("resonantinduction.quantum.gate.IQuantumGate", "resonantinduction.quantum.gate.TraitQuantumGate");
		MultipartGenerator.registerTrait("universalelectricity.api.energy.IConductor", "resonantinduction.electrical.wire.trait.TraitConductor");
		MultipartGenerator.registerTrait("cofh.api.energy.IEnergyHandler", "resonantinduction.electrical.wire.trait.TraitEnergyHandler");
		MultipartGenerator.registerTrait("ic2.api.energy.tile.IEnergySink", "resonantinduction.electrical.wire.trait.TraitEnergySink");
	}

	@Override
	public TMultiPart createPart(String name, boolean client)
	{
		if (name.equals("resonant_induction_wire"))
			return new PartFramedWire();
		else if (name.equals("resonant_induction_switch_wire"))
			return new PartFramedSwitchWire();
		else if (name.equals("resonant_induction_flat_wire"))
			return new PartFlatWire();
		else if (name.equals("resonant_induction_flat_switch_wire"))
			return new PartFlatSwitchWire();
		else if (name.equals("resonant_induction_multimeter"))
			return new PartMultimeter();
		else if (name.equals("resonant_induction_transformer"))
			return new PartTransformer();
		else if (name.equals("resonant_induction_charger"))
			return new PartCharger();
		else if (name.equals("resonant_induction_levitator"))
			return new PartLevitator();
		else if (name.equals("resonant_induction_quantum_glyph"))
			return new PartQuantumGlyph();
		else if (name.equals("resonant_induction_itemrailing"))
			return new PartRailing();

		return null;
	}
}
