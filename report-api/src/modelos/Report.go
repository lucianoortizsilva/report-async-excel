package modelos

import (
	"errors"
)

type Report struct {
	ReleaseYear uint64 `json:"releaseYear,omitempty"`
	EmailTo     string `json:"emailTo,omitempty"`
}

func (u *Report) Preparar() error {
	if erro := u.validar(); erro != nil {
		return erro
	}
	return nil
}

func (u *Report) validar() error {

	if u.EmailTo == "" {
		return errors.New("obrigatório informar e-mail")
	}

	if u.ReleaseYear == 0 {
		return errors.New("obrigatório informar um ano válido")
	}

	return nil
}
