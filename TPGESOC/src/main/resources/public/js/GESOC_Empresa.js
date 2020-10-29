const APP = getEl('app'),
      SETTINGS_BUTTON = getEl('settings-button'),
      CLOSE_BUTTON = getEl('close-button')

const toggleSettings = () => {
  if(hasClass(APP, 'settings-toggled')){
    removeClass(APP, 'settings-toggled')
  }
  else{
    addClass(APP, 'settings-toggled')
  }
}

SETTINGS_BUTTON.onclick = () => {
  toggleSettings()
}

CLOSE_BUTTON.onclick = () => {
  toggleSettings()
}

window.onload = () => {
  setTimeout(() => {
    toggleSettings()
  }, 500)
}