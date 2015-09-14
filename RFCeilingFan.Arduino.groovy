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
		command "light1On"
        command "light1Off"
		command "light1Toggle"
		
        command "fan2Off"
        command "fan2Low"
        command "fan2Medium"
        command "fan2High"
		command "light2On"
        command "light2Off"		
		command "light2Toggle"
        
       	command "fan3Off"
        command "fan3Low"
        command "fan3Medium"
        command "fan3High"
		command "light3On"
        command "light3Off"
		command "light3Toggle"
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
        standardTile("light1", "device.light1", inactiveLabel: false, decoration: "flat") {            
            state "off", label: '${name}', action: "light1On", icon:"st.Lighting.light11", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: '${name}', action: "light1Off", icon:"st.Lighting.light11", backgroundColor: "#79b821", nextState: "off" 
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
        standardTile("light2", "device.light2", inactiveLabel: false, decoration: "flat") {            
            state "off", label: '${name}', action: "light2On", icon:"st.Lighting.light11", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: '${name}', action: "light2Off", icon:"st.Lighting.light11", backgroundColor: "#79b821", nextState: "off" 
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
        standardTile("light3", "device.light3", inactiveLabel: false, decoration: "flat") {            
            state "off", label: '${name}', action: "light3On", icon:"st.Lighting.light11", backgroundColor: "#ffffff", nextState: "on"
			state "on", label: '${name}', action: "light3Off", icon:"st.Lighting.light11", backgroundColor: "#79b821", nextState: "off" 
        }		
		standardTile("fan3off", "device.fan3off", canChangeIcon: false, inactiveLabel: false, decoration: "flat") {
			state "off", label: "", action: "fan3Off", icon:"st.thermostat.fan-off"		 
		} 
   
		main "fan1"
		details(["fan1", "light1", "fan1off", "fan2", "light2", "fan2off", "fan3", "light3", "fan3off"])
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
def light1On() {	 	 
	sendEvent(name: "light1", value: "on", isStateChange: true, display: false)	  
	light1Toggle()
}
def light1Off() {	  	 
	sendEvent(name: "light1", value: "off", isStateChange: true, display: false)    	
    light1Toggle()
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
def light2On() {	 	 
	sendEvent(name: "light2", value: "on", isStateChange: true, display: false)	  
	light2Toggle()
}
def light2Off() {	  	 
	sendEvent(name: "light2", value: "off", isStateChange: true, display: false)    	
    light2Toggle()
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
def light3On() {	 	 
	sendEvent(name: "light3", value: "on", isStateChange: true, display: false)	  
	light3Toggle()
}
def light3Off() {	  	 
	sendEvent(name: "light3", value: "off", isStateChange: true, display: false)    		
    light3Toggle()
}
def light3Toggle() { 
	log.info "Light 3 toggle"
    zigbee.smartShield(text: "fan3light").format()
}