/*Device type for use by arduino shield, goes along with smartapp and virtual device
*/
metadata {

	definition (name: "RF Ceiling Fan Arduino", namespace: "tamttt", author: "tamttt") {
        capability "Switch"
        capability "Momentary"  

		attribute "fan1", "string"
		attribute "fan1off", "string"
		attribute "light1", "string"
		
		attribute "fan2", "string"
		attribute "fan2off", "string"
		attribute "light2", "string"

		attribute "fan3", "string"
		attribute "fan3off", "string"
		attribute "light3", "string"		
		                
		command "fan1Off"
        command "fan1Low"
        command "fan1Medium"
        command "fan1High"		
		command "light1Toggle"
		
        command "fan2Off"
        command "fan2Low"
        command "fan2Medium"
        command "fan2High"				
		command "light2Toggle"
        
       	command "fan3Off"
        command "fan3Low"
        command "fan3Medium"
        command "fan3High"		
		command "light3Toggle"
		
		command "fan4Off"
        command "fan4Low"
        command "fan4Medium"
        command "fan4High"		
		command "light4Toggle"
	}
    
    ////switchs are push style switches because arduino isn't able to tell if the fans are on or not. 
    tiles {
//fan 1	
		standardTile("fan1", "device.fan1", width: 2, height: 2, canChangeIcon: true) {        	
        	state "off", label:'OFF', action:"fan1Low", icon:"st.Lighting.light24", backgroundColor:"#ffffff", nextState: "low"
			state "low", label:'LOW', action:"fan1Medium", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "medium"
			state "medium", label:'MEDIUM', action:"fan1High", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "high"
			state "high", label:'HIGH', action:"fan1Off", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "off"			
		}	
		
        standardTile("light1", "device.light1", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {            
            state "off", label: "ON/OFF", action: "light1Toggle", icon:"st.Lighting.light13"			
        }		
		
		standardTile("fan1off", "device.fan1off", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "off", label: "", action: "fan1Off", icon:"st.thermostat.fan-off"		 
		} 
//fan 2	
   		standardTile("fan2", "device.fan2", width: 2, height: 2, canChangeIcon: true) {        	
        	state "off", label:'OFF', action:"fan2Low", icon:"st.Lighting.light24", backgroundColor:"#ffffff", nextState: "low"
			state "low", label:'LOW', action:"fan2Medium", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "medium"
			state "medium", label:'MEDIUM', action:"fan2High", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "high"
			state "high", label:'HIGH', action:"fan2Off", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "off"			
		}	
        standardTile("light2", "device.light2", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {            
            state "off", label: "ON/OFF", action: "light2Toggle", icon:"st.Lighting.light13"			
        }			
		standardTile("fan2off", "device.fan2off", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "off", label: "", action: "fan2Off", icon:"st.thermostat.fan-off"		 
		} 
// fan 3		
		standardTile("fan3", "device.fan3", width: 2, height: 2, canChangeIcon: true) {        	
        	state "off", label:'OFF', action:"fan3Low", icon:"st.Lighting.light24", backgroundColor:"#ffffff", nextState: "low"
			state "low", label:'LOW', action:"fan3Medium", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "medium"
			state "medium", label:'MEDIUM', action:"fan3High", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "high"
			state "high", label:'HIGH', action:"fan3Off", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "off"			
		}	
        standardTile("light3", "device.light3", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {            
            state "off", label: "ON/OFF", action: "light3Toggle", icon:"st.Lighting.light13"			
        }			
		standardTile("fan3off", "device.fan3off", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "off", label: "", action: "fan3Off", icon:"st.thermostat.fan-off"		 
		} 
		
// fan 4		
		standardTile("fan4", "device.fan4", width: 2, height: 2, canChangeIcon: true) {        	
        	state "off", label:'OFF', action:"fan4Low", icon:"st.Lighting.light24", backgroundColor:"#ffffff", nextState: "low"
			state "low", label:'LOW', action:"fan4Medium", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "medium"
			state "medium", label:'MEDIUM', action:"fan4High", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "high"
			state "high", label:'HIGH', action:"fan4Off", icon:"st.Lighting.light24", backgroundColor:"#79b821", nextState: "off"			
		}	
        standardTile("light4", "device.light4", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {            
            state "off", label: "ON/OFF", action: "light4Toggle", icon:"st.Lighting.light14"			
        }			
		standardTile("fan4off", "device.fan4off", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "off", label: "", action: "fan4Off", icon:"st.thermostat.fan-off"		 
		} 
   
		main "fan1"
		details(["fan1", "light1", "fan1off", "fan2", "light2", "fan2off", "fan3", "light3", "fan3off", "fan4", "light4", "fan4off"])
	}      
}

//Command is simply received and sent over to the arduino shield, relay command are done within the arduino
def fan1Off() {
    sendEvent(name: "fan1", value: "off", isStateChange: true, display: false)//turns the switch back off
	log.debug "Turning Fan 1 Off"
    zigbee.smartShield(text: "fan1off").format()//send command over to arduino
} 
def fan1Low() {
    sendEvent(name: "fan1", value: "low", isStateChange: true, display: false)
	log.debug "Turning Fan 1 to Low"
    zigbee.smartShield(text: "fan1low").format()
}
def fan1Medium() {
	sendEvent(name: "fan1", value: "medium", isStateChange: true, display: false)
	log.debug "Turning Fan 1 to Medium"
    zigbee.smartShield(text: "fan1medium").format()
}
def fan1High() {
	sendEvent(name: "fan1", value: "high", isStateChange: true, display: false)
	log.debug "Turning Fan 1 to High"
    zigbee.smartShield(text: "fan1high").format()
}

def light1Toggle() { 
	log.info "Light 1 toggle" 
    zigbee.smartShield(text: "fan1light").format()
}

def fan2Off() {
    sendEvent(name: "fan2", value: "off", isStateChange: true, display: false)
	log.debug "Turning Fan 2 Off"
    zigbee.smartShield(text: "fan2off").format()
} 
def fan2Low() {
    sendEvent(name: "fan2", value: "low", isStateChange: true, display: false)
	log.debug "Turning Fan 2 to Low"
    zigbee.smartShield(text: "fan2low").format()
}
def fan2Medium() {
	sendEvent(name: "fan2", value: "medium", isStateChange: true, display: false)
	log.debug "Turning Fan 2 to Medium"
    zigbee.smartShield(text: "fan2medium").format()
}
def fan2High() {
	sendEvent(name: "fan2", value: "high", isStateChange: true, display: false)
	log.debug "Turning Fan 2 to High"
    zigbee.smartShield(text: "fan2high").format()
}
def light2Toggle() { 
	log.info "Light 2 toggle" 
    zigbee.smartShield(text: "fan2light").format()
}

def fan3Off() {
    sendEvent(name: "fan3", value: "off", isStateChange: true, display: false)//turns the switch back off
	log.debug "Turning Fan 3 Off"
    zigbee.smartShield(text: "fan3off").format()//send command over to arduino
} 
def fan3Low() {
    sendEvent(name: "fan3", value: "low", isStateChange: true, display: false)
	log.debug "Turning Fan 3 to Low"
    zigbee.smartShield(text: "fan3low").format()
}
def fan3Medium() {
	sendEvent(name: "fan3", value: "medium", isStateChange: true, display: false)
	log.debug "Turning Fan 3 to Medium"
    zigbee.smartShield(text: "fan3medium").format()
}
def fan3High() {
	sendEvent(name: "fan3", value: "high", isStateChange: true, display: false)
	log.debug "Turning Fan 3 to High"
    zigbee.smartShield(text: "fan3high").format()
}
def light3Toggle() { 
	log.info "Light 3 toggle"
    zigbee.smartShield(text: "fan3light").format()
}

def fan4Off() {
    sendEvent(name: "fan4", value: "off", isStateChange: true, display: false)//turns the switch back off
	log.debug "Turning Fan 4 Off"
    zigbee.smartShield(text: "fan4off").format()//send command over to arduino
} 
def fan4Low() {
    sendEvent(name: "fan4", value: "low", isStateChange: true, display: false)
	log.debug "Turning Fan 4 to Low"
    zigbee.smartShield(text: "fan4low").format()
}
def fan4Medium() {
	sendEvent(name: "fan4", value: "medium", isStateChange: true, display: false)
	log.debug "Turning Fan 4 to Medium"
    zigbee.smartShield(text: "fan4medium").format()
}
def fan4High() {
	sendEvent(name: "fan4", value: "high", isStateChange: true, display: false)
	log.debug "Turning Fan 4 to High"
    zigbee.smartShield(text: "fan4high").format()
}
def light4Toggle() { 
	log.info "Light 4 toggle"
    zigbee.smartShield(text: "fan4light").format()
}